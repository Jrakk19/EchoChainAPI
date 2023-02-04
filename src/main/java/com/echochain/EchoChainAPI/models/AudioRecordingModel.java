package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class AudioRecordingModel {

    private UUID id;
    private UUID playerId;
    private UUID s3Key;
    private int gameIndex;

    private UUID roomId;
    


    public AudioRecordingModel(UUID id, UUID playerId, UUID s3Key, int gameIndex, UUID roomId) {
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

    public UUID getS3Key() {
        return s3Key;
    }

    public void setS3Key(UUID s3Key) {
        this.s3Key = s3Key;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }
}
