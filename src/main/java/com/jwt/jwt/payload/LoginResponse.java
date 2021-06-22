package com.jwt.jwt.payload;

/**
 * Created by Anuj kumar pal
 */
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String developer = "Anuj kumar pal";
    private Long loggedInUserId;
    public LoginResponse(String accessToken, Long loggedInUserId) {
        this.accessToken = accessToken;
        this.loggedInUserId = loggedInUserId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Long getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(Long loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }
}
