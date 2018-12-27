package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("data")
public class FacebookDebug {

    @JsonProperty("type")
    private String type;

    @JsonProperty("app_id")
    private String app_id;

    @JsonProperty("expires_at")
    private Long expires_at;

    @JsonProperty("is_valid")
    private Boolean is_valid;

    @JsonProperty("user_id")
    private String user_id;

    public FacebookDebug(@JsonProperty("type") String type, @JsonProperty("app_id") String app_id, @JsonProperty("expires_at") Long expires_at,@JsonProperty("is_valid") Boolean is_valid,@JsonProperty("user_id") String user_id) {
        this.type = type;
        this.app_id = app_id;
        this.expires_at = expires_at;
        this.is_valid = is_valid;
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public String getApp_id() {
        return app_id;
    }

    public Long getExpires_at() {
        return expires_at;
    }

    public Boolean getIs_valid() {
        return is_valid;
    }

    public String getUser_id() {
        return user_id;
    }

}
