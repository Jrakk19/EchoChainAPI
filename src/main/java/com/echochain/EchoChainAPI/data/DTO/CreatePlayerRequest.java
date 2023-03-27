package com.echochain.EchoChainAPI.data.DTO;

import com.echochain.EchoChainAPI.models.PromptModel;

public class CreatePlayerRequest {
    private String displayName;
    private String roomCode;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

}
