package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class PromptModel {
    private UUID id;
    private String title;
    private UUID roomId;

    private int gameIndex;

    public PromptModel(UUID id, String title, UUID roomId, int gameIndex) {
        this.id = id;
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
    }

    public PromptModel(String title, UUID roomId, int gameIndex) {
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
    }

    public PromptModel() {
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
