package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.models.PlayerModel;
import com.echochain.EchoChainAPI.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService service;

    /**
     * Get all Players in the DB
     * @return - Return a list of player models from the DB
     */
    @GetMapping("/all")
    public List<PlayerModel> getAllPlayers(){

        List<PlayerEntity> playerEntities = service.findAll();

        List<PlayerModel> playerModels = new ArrayList<>();

        playerEntities.forEach(playerEntity -> {
            playerModels.add(new PlayerModel(playerEntity.getId(), playerEntity.getGameId(), playerEntity.getDisplayName(),
                    playerEntity.getPoints(), playerEntity.getAvatarUrl()));
        });

        return playerModels;
    }

    /**
     * Find a player by its Id
     * @param id - Id of the player you are trying to find
     * @return - Returns the Player from the DB as a Player Model
     */
    @GetMapping("/{id}")
    public PlayerModel findPlayerById(@PathVariable UUID id){

        PlayerEntity playerEntity = service.findById(id);

        PlayerModel playerModel = new PlayerModel(playerEntity.getId(), playerEntity.getGameId(),
                playerEntity.getDisplayName(), playerEntity.getPoints(), playerEntity.getAvatarUrl());

        return playerModel;
    }

    /**
     * Create a Player in the DB
     * @param player - The Player that will be created in the Database
     * @return - returns a 1 if successful and a 0 if unsuccessful
     */
    @PostMapping("/create")
    public int createPlayer(@RequestBody PlayerModel player){
        return service.createPlayer(player);
    }

    /**
     * Delete a Player from the database by its Id
     * @param id - the Id that will be used to delete the player
     * @return - returns a 1 if successful and a 0 if unsuccessful
     */
    @DeleteMapping("/{id}")
    public int deletePlayer(@PathVariable UUID id){
        return service.deletePlayer(id);
    }

    /**
     * Updates a player in the database
     * @param id - Id of the player that will be updated
     * @param playerModel - The player info that will be used to update the player in the DB
     * @return - Returns the Updated player from the DB
     */
    @PutMapping("/{id}")
    public PlayerModel updatePlayer(@PathVariable UUID id, @RequestBody PlayerModel playerModel){

        PlayerEntity playerEntity = service.updatePlayerInfo(playerModel);

        PlayerModel newPlayerModel = new PlayerModel(playerEntity.getId(), playerEntity.getGameId(),
                playerEntity.getDisplayName(), playerEntity.getPoints(), playerEntity.getAvatarUrl());

        return newPlayerModel;
    }
}
