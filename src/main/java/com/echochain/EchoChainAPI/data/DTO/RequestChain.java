package com.echochain.EchoChainAPI.data.DTO;

import java.util.UUID;

public class RequestChain {

    private UUID roomId;
    private int playerNumber;

    public RequestChain(UUID roomId, int playerNumber) {
        this.roomId = roomId;
        this.playerNumber = playerNumber;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
