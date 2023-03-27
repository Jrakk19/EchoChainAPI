package com.echochain.EchoChainAPI.data.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "players")
public class PlayerEntity {

    @Id
    private UUID id;

    @Column("room_id")
    private UUID gameId;

    @Column("display_name")
    private String displayName;

    @Column("points")
    private int points;

    @Column("avatar")
    private String avatarUrl;

    @Column("player_number")
    private int playerNumber;


    public PlayerEntity(UUID gameId, String displayName, int points, String avatarUrl, int playerNumber) {
        this.gameId = gameId;
        this.displayName = displayName;
        this.points = points;
        this.avatarUrl = avatarUrl;
        this.playerNumber = playerNumber;
    }
    public PlayerEntity(UUID id, UUID gameId, String displayName, int points, String avatarUrl, int playerNumber) {
        this.id = id;
        this.gameId = gameId;
        this.displayName = displayName;
        this.points = points;
        this.avatarUrl = avatarUrl;
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public PlayerEntity() {
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
