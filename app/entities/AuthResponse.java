package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {

    @JsonProperty("is_valid")
    private Boolean is_valid;

    @JsonProperty("expiration_date")
    private Long expiration_date;

    public AuthResponse(@JsonProperty("is_valid") Boolean is_valid, @JsonProperty("expiration_date") Long expiration_date) {
        this.is_valid = is_valid;
        this.expiration_date = expiration_date;
    }

    @JsonProperty("is_valid")
    public Boolean getIs_valid() {
        return is_valid;
    }

    @JsonProperty("expiration_date")
    public Long getExpirationDate() {
        return expiration_date;
    }
}
