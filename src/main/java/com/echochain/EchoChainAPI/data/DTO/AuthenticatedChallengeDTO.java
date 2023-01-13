package com.echochain.EchoChainAPI.data.DTO;


public class AuthenticatedChallengeDTO {


    private String sessionId;

    private String username;

    private String challengeType;

    public AuthenticatedChallengeDTO(String sessionId, String username, String challengeType) {
        this.sessionId = sessionId;
        this.username = username;
        this.challengeType = challengeType;
    }

    public AuthenticatedChallengeDTO() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(String challengeType) {
        this.challengeType = challengeType;
    }
}
