package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class PromptModel {
    private UUID id;
    private String title;
    private UUID roomId;

    private int gameIndex;

    private UUID chainId;

    private UUID playerId;

    public PromptModel(UUID id, String title, UUID roomId, int gameIndex, UUID chainId) {
        this.id = id;
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
    }

    public PromptModel(String title, UUID roomId, int gameIndex, UUID chainId, UUID playerId) {
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
        this.playerId = playerId;
    }

    public PromptModel() {
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public UUID getChainId() {
        return chainId;
    }

    public void setChainId(UUID chainId) {
        this.chainId = chainId;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
