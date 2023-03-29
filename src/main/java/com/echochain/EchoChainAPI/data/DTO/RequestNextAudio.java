package com.echochain.EchoChainAPI.data.DTO;

import com.echochain.EchoChainAPI.models.PlayerModel;

public class RequestNextAudio {
    private int gameIndex;
    private PlayerModel player;

    public RequestNextAudio(int gameIndex, PlayerModel player) {
        this.gameIndex = gameIndex;
        this.player = player;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel playerNumber) {
        this.player = playerNumber;
    }
}
