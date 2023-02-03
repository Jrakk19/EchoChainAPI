package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class AudioRecordingModel {

    private UUID id;
    private UUID playerId;
    private String s3Key;
    private int gameIndex;

    private UUID roomId;
    


    public AudioRecordingModel(UUID id, UUID playerId, String s3Key, int gameIndex, UUID roomId) {
        this.id = id;
        this.playerId = playerId;
        this.s3Key = s3Key;
        this.gameIndex = gameIndex;
        this.roomId = roomId;
    }

    public AudioRecordingModel() {
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
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

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }
}
