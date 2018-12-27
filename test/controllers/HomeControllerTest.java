package controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.FacebookDebug;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testRun(){
        String data = "{\"data\":{\"app_id\":\"317691122154800\",\"type\":\"USER\",\"application\":\"gimme\",\"data_access_expires_at\":1549321000,\"expires_at\":1546725837,\"is_valid\":true,\"issued_at\":1541541837,\"metadata\":{\"auth_type\":\"rerequest\",\"sso\":\"chrome_custom_tab\"},\"scopes\":[\"email\",\"public_profile\"],\"user_id\":\"2429400133748891\"}}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        try {
            FacebookDebug debug = mapper.readValue(data,FacebookDebug.class);
            System.out.println("debug: "+debug.getApp_id());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
