package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class RoomModel {

    private UUID id;
    private String code;
    private String gameName;
    private int gameState;

    public RoomModel(UUID id, String code, String gameName, int gameState) {
        this.id = id;
        this.code = code;
        this.gameName = gameName;
        this.gameState = gameState;
    }

    public RoomModel(String code, String gameName, int gameState) {
        this.code = code;
        this.gameName = gameName;
        this.gameState = gameState;
    }

    public RoomModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    @Override
    public String toString() {
        return "RoomModel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameState='" + gameState + '\'' +
                '}';
    }
}
