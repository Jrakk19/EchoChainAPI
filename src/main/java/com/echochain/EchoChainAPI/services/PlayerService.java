package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.repository.PlayerRepository;
import com.echochain.EchoChainAPI.models.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerEntity> findAll() {

        List<PlayerEntity> playerEntities = new ArrayList<>();

        Iterable<PlayerEntity> players = playerRepository.findAll();

        players.forEach(playerEntities::add);

        return playerEntities;
    }

    public PlayerEntity findById(UUID id){
        Optional<PlayerEntity> playerEntity = playerRepository.findById(id);

        if(playerEntity.isPresent()){
            return playerEntity.get();
        }else{
            return null;
        }
    }

    public int createPlayer(PlayerModel playerModel){

        PlayerEntity playerEntity = new PlayerEntity(playerModel.getId(), playerModel.getGameId(),
                playerModel.getDisplayName(), playerModel.getPoints(), playerModel.getAvatarUrl());

        try{
            playerRepository.save(playerEntity);

            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    public int deletePlayer(UUID id){
        try{
            playerRepository.deleteById(id);
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    public PlayerEntity updatePlayerInfo(PlayerModel playerModel){

        PlayerEntity playerEntity = new PlayerEntity(playerModel.getId(), playerModel.getGameId(),
                playerModel.getDisplayName(), playerModel.getPoints(), playerModel.getAvatarUrl());

        return playerRepository.save(playerEntity);
    }
}
