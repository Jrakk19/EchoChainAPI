package com.echochain.EchoChainAPI.data.entities;




import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name="guesses")
public class GuessEntity {

    @Id
    private UUID id;

    @Column("game_id")
    private UUID gameId;

    @Column("room_id")
    private UUID roomId;

    @Column("game_index")
    private int gameIndex;

    public GuessEntity(UUID id, UUID gameId, UUID roomId, int gameIndex) {
        this.id = id;
        this.gameId = gameId;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
    }

    public GuessEntity() {
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