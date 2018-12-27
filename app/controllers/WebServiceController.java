package controllers;

import akka.stream.Materializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.RemoteSession;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import services.FacebookServiceExecutor;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

// #ws-imports
// #json-imports
// #ws-client-imports

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class WebServiceController extends Controller {

    private ObjectMapper MAPPER = new ObjectMapper();

    @Inject WSClient ws;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> getSession(){
        JsonNode data = request().body().asJson();
        RemoteSession session = null;
        FacebookServiceExecutor facebookServiceExecutor = new FacebookServiceExecutor(ws);

        try {
            session = MAPPER.readValue(data.toString(),RemoteSession.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return facebookServiceExecutor.getAuthorizationInfo(session);
    }

    public CompletionStage<Result> getUserInfo(){
        //TODO -> Check authorization (based on the stored token)

        JsonNode data = request().body().asJson();
        RemoteSession session = null;
        FacebookServiceExecutor facebookServiceExecutor = new FacebookServiceExecutor(ws);

        try {
            session = MAPPER.readValue(data.toString(),RemoteSession.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return facebookServiceExecutor.getUserInfo(session);
    }

}
