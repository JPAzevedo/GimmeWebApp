package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.AuthResponse;
import entities.FacebookDebug;
import entities.RemoteSession;
import org.apache.commons.codec.digest.DigestUtils;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Result;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.unauthorized;

public class FacebookServiceExecutor {

    private static Duration TIMEOUT = Duration.ofSeconds(5);

    private WSClient ws;

    private ObjectMapper MAPPER = new ObjectMapper();


    public  FacebookServiceExecutor(WSClient ws){
        this.ws = ws;
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    }


    private CompletableFuture<? extends Result> getUnexpectedHandler(Result callback){
        return new CompletableFuture<>().supplyAsync(() -> callback);
    }

    public CompletionStage<Result> getAuthorizationInfo(RemoteSession remoteSession){
        String url = FacebookURLManager.getFacebookDebugURL(remoteSession);

        if(url == null){
            return (CompletionStage<Result>) getUnexpectedHandler(badRequest("Invalid url"));
        }

        CompletionStage<WSResponse> futureResponse =
                ws.url(url).setRequestTimeout(TIMEOUT).get();

        return executeTokenValidation(futureResponse,remoteSession);

    }

    public CompletionStage<Result> getUserInfo(RemoteSession remoteSession){

        String url = FacebookURLManager.getUserInfoURL(remoteSession);

        if(url == null){
            return (CompletionStage<Result>) getUnexpectedHandler(badRequest("Invalid url"));
        }

        CompletionStage<WSResponse> futureResponse =
                ws.url(url).setRequestTimeout(TIMEOUT).get();

        return executeUserDataRequest(futureResponse);

    }

    private CompletionStage<Result> executeTokenValidation(CompletionStage<WSResponse> futureResponse, RemoteSession remoteSession){

       return futureResponse.handle((wsResponse, throwable) -> {

                if (throwable != null) {
                    return badRequest("Error");
                } else {
                    if (wsResponse.getStatus() == 200) {
                        FacebookDebug facebookDebug = getFacebookDebugData(wsResponse.getBody());
                        if(facebookDebug == null || !facebookDebug.getIs_valid()){
                            return unauthorized("Invalid credentials");
                        }

                        //TODO -> Save token
                        generateToken(remoteSession.getToken());
                        AuthResponse authResponse = new AuthResponse(facebookDebug.getIs_valid(),facebookDebug.getExpires_at());
                        try {
                            String response = MAPPER.writeValueAsString(authResponse);
                            return ok(response);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                        return badRequest("error parsing response");

                    } else {
                        return badRequest("http error: "+wsResponse.getStatus());
                    }
                }
        });

    }

    private CompletionStage<Result> executeUserDataRequest(CompletionStage<WSResponse> futureResponse){
        return  futureResponse.handle((wsResponse, throwable) -> {
            if (throwable != null) {
                return badRequest("Error");
            } else {
                if (wsResponse.getStatus() == 200) {
                        return ok(wsResponse.getBody());
                } else {
                    return badRequest("http error: "+wsResponse.getStatus());
                }
            }
        });
    }

    public FacebookDebug getFacebookDebugData(String body){
        if(body == null){
            return null;
        }

        try {

            FacebookDebug facebookDebug = MAPPER.readValue(body, FacebookDebug.class);
            return facebookDebug;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String generateToken(String token)
    {
        return DigestUtils.sha256Hex(token);
    }
}
