package com.echochain.EchoChainAPI.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "audio_recordings")
public class AudioRecordingEntity {

    @Id
    private UUID id;

    @Column("player_id")
    private UUID playerId;

    @Column("s3_key")
    private UUID s3Key;

    @Column("game_index")
    private int gameIndex;

    @Column("room_id")
    private UUID roomId;

    @Column("chain_id")
    private UUID chainId;


    public AudioRecordingEntity(UUID id, UUID playerId, UUID s3Key, int gameIndex, UUID roomId, UUID chainId) {
        this.id = id;
        this.playerId = playerId;
        this.s3Key = s3Key;
        this.gameIndex = gameIndex;
        this.roomId = roomId;
        this.chainId = chainId;
    }

    public AudioRecordingEntity(UUID playerId, UUID s3Key, int gameIndex, UUID roomId) {
        this.playerId = playerId;
        this.s3Key = s3Key;
        this.gameIndex = gameIndex;
        this.roomId = roomId;
        this.chainId = chainId;
    }

    public AudioRecordingEntity() {
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
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public UUID getS3Key() {
        return s3Key;
    }

    public void setS3Key(UUID s3Key) {
        this.s3Key = s3Key;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }
}
