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

    @Column("chain_id")
    private UUID chainId;

    public GuessEntity(UUID id, UUID gameId, UUID roomId, int gameIndex, UUID chainId) {
        this.id = id;
        this.gameId = gameId;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.chainId = chainId;
    }

    public GuessEntity() {
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
