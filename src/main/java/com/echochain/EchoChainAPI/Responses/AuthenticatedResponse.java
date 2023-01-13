package com.echochain.EchoChainAPI.Responses;

import java.io.Serializable;


public class AuthenticatedResponse implements Serializable {

    private String username;

    private String accessToken;

    private String idToken;

    private String refreshToken;


    public AuthenticatedResponse(String username, String accessToken, String idToken, String refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
    }

    public AuthenticatedResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
