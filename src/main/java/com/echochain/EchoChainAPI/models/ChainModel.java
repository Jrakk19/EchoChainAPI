package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class ChainModel {

    private UUID id;

    private UUID room_id;

    public ChainModel(UUID id, UUID room_id) {
        this.id = id;
        this.room_id = room_id;
    }

    public ChainModel(UUID room_id) {
        this.room_id = room_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRoom_id() {
        return room_id;
    }

    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }
}
