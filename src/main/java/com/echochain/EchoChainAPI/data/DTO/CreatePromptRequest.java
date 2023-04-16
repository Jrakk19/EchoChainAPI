package com.echochain.EchoChainAPI.data.DTO;

import com.echochain.EchoChainAPI.models.PromptModel;

import java.util.UUID;

public class CreatePromptRequest {

    private String title;
    private UUID roomId;
    private int gameIndex;

    private UUID playerId;

    public CreatePromptRequest(String title, UUID roomId, int gameIndex, UUID playerId) {
        this.title = title;
        this.roomId = roomId;
        this.gameIndex = gameIndex;
        this.playerId = playerId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public PromptModel requestToModel(CreatePromptRequest prompt, UUID chainId){
        PromptModel promptModel = new PromptModel(prompt.getTitle(), prompt.getRoomId(), prompt.getGameIndex(), chainId, prompt.getPlayerId());
        return promptModel;
    }
}
