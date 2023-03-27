package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class PlayerModel {
    private UUID id;
    private UUID gameId;
    private String displayName;
    private int points;
    private String avatarUrl;

    private int playerNumber;
    public PlayerModel(UUID id, UUID gameId, String displayName, int points, String avatarUrl, int playerNumber) {
        this.id = id;
        this.gameId = gameId;
        this.displayName = displayName;
        this.points = points;
        this.avatarUrl = avatarUrl;
        this.playerNumber = playerNumber;
    }

    public PlayerModel() {
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
