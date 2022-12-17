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
    private String s3Key;

    @Column("game_index")
    private String gameIndex;

    public AudioRecordingEntity(UUID id, UUID playerId, String s3Key, String gameIndex) {
        this.id = id;
        this.playerId = playerId;
        this.s3Key = s3Key;
        this.gameIndex = gameIndex;
    }

    public AudioRecordingEntity() {
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

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public String getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(String gameIndex) {
        this.gameIndex = gameIndex;
    }
}
