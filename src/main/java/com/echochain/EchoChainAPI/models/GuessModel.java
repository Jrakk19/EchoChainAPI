package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class GuessModel {

    private UUID id;
    private UUID gameId;
    private UUID roomId;
    private int gameIndex;

    public GuessModel(UUID id, UUID gameId, UUID roomId, int gameIndex) {
        this.id = id;
        this.gameId = gameId;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
    }

    public GuessModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }
}
