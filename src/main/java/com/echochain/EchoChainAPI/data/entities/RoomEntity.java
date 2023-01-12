package com.echochain.EchoChainAPI.data.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "room")
public class RoomEntity {

    @Id
    private UUID id;

    @Column("code")
    private String code;

    @Column("game_name")
    private String gameName;

    @Column("state")
    private String gameState;

    public RoomEntity(UUID id, String code, String gameName, String gameState) {
        this.id = id;
        this.code = code;
        this.gameName = gameName;
        this.gameState = gameState;
    }

    public RoomEntity(String code, String gameName, String gameState){
        this.code = code;
        this.gameName = gameName;
        this.gameState = gameState;
    }
    public RoomEntity() {
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

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }


}
