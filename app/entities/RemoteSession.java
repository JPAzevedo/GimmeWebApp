package entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteSession{

    @JsonProperty("access_token")
    private String token;

    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("app_id")
    private String app_id;

    public RemoteSession(@JsonProperty("access_token") String token, @JsonProperty("user_d") String user_id, @JsonProperty("app_id") String app_id){
        this.token = token;
        this.user_id = user_id;
        this.app_id = app_id;
    }

    @JsonGetter("access_token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonGetter("user_id")
    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    @JsonGetter("app_id")
    public String geAppId() {
        return app_id;
    }

    public void setAppId(String app_id) {
        this.app_id = app_id;
    }
}