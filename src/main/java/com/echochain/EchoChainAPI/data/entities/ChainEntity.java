package com.echochain.EchoChainAPI.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "chains")
public class ChainEntity {

    @Id
    private UUID id;

    @Column("room_id")
    private UUID roomId;

    public ChainEntity(UUID id, UUID roomId) {
        this.id = id;
        this.roomId = roomId;
    }

    public ChainEntity(UUID roomId) {
        this.roomId = roomId;
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
}
