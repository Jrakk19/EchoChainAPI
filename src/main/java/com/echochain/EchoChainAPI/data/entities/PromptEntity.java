package com.echochain.EchoChainAPI.data.entities;

import org.checkerframework.checker.units.qual.C;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "prompts")
public class PromptEntity {

    @Id
    private UUID id;
    @Column("title")
    private String title;

    @Column("room_id")
    private UUID roomId;

    @Column("game_stage")
    private int gameIndex;

    @Column("chain_id")
    private UUID chainId;
    @Column("player_id")
    private UUID playerId;

    public PromptEntity(UUID id, String title, UUID roomId, int gameIndex, UUID chainId) {
        this.id = id;
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
    }

    public PromptEntity(String title, UUID roomId, int gameIndex, UUID chainId, UUID playerId) {
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
        this.playerId = playerId;
    }

    public PromptEntity() {
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
