package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class GuessModel {

    private UUID id;
    private UUID gameId;
    private UUID roomId;
    private int gameIndex;

    private UUID chainId;

    public GuessModel(UUID id, UUID gameId, UUID roomId, int gameIndex, UUID chainId) {
        this.id = id;
        this.gameId = gameId;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
    }

    public GuessModel() {
    }

    public UUID getChainId() {
        return chainId;
    }

    public void setChainId(UUID chainId) {
        this.chainId = chainId;
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
