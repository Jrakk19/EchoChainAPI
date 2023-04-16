package com.echochain.EchoChainAPI.data.entities;




import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name="guesses")
public class GuessEntity {

    @Id
    private UUID id;

    @Column("title")
    private String title;

    @Column("room_id")
    private UUID roomId;

    @Column("game_index")
    private int gameIndex;

    @Column("chain_id")
    private UUID chainId;

    @Column("player_id")
    private UUID playerId;

    public GuessEntity(UUID id,UUID roomId, int gameIndex, UUID chainId, String title, UUID playerId) {
        this.id = id;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
        this.title = title;
        this.playerId = playerId;
    }

    public GuessEntity() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
