package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class AudioRecordingModel {

    private UUID id;
    private UUID playerId;
    private String s3Key;
    private String gameIndex;

    public AudioRecordingModel(UUID id, UUID playerId, String s3Key, String gameIndex) {
        this.id = id;
        this.playerId = playerId;
        this.s3Key = s3Key;
        this.gameIndex = gameIndex;
    }

    public AudioRecordingModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public String getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(String gameIndex) {
        this.gameIndex = gameIndex;
    }
}
