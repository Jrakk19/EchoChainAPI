package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class GuessModel {

    private UUID id;

    private String title;
    private UUID roomId;
    private int gameIndex;

    private UUID chainId;

    private UUID playerId;

    public GuessModel(UUID id, UUID roomId, int gameIndex, UUID chainId, String title, UUID playerId) {
        this.id = id;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
        this.title = title;
        this.playerId = playerId;
    }

    public GuessModel(String title, UUID roomId, int gameIndex, UUID chainId, UUID playerId) {
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
        this.playerId = playerId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
