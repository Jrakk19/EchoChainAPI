package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.data.repository.PlayerRepository;
import com.echochain.EchoChainAPI.data.repository.RoomRepository;
import com.echochain.EchoChainAPI.models.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<PlayerEntity> findAll() {

        List<PlayerEntity> playerEntities = new ArrayList<>();

        Iterable<PlayerEntity> players = playerRepository.findAll();

        players.forEach(playerEntities::add);

        return playerEntities;
    }

    public List<PlayerEntity> findPlayersInRoom(UUID roomId){
        List<PlayerEntity> playerEntities = new ArrayList<>();

        Iterable<PlayerEntity> players = playerRepository.findPlayersInRoom(roomId);

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

    public PlayerEntity createPlayer(String displayName, String roomCode){
        RoomEntity roomEntity = new RoomEntity();
        try{
            roomEntity = roomRepository.findByRoomId(roomCode);
        }catch(Exception e){
            e.printStackTrace();
        }

        PlayerEntity playerEntity = new PlayerEntity(roomEntity.getId(),displayName, 0, "", 0 );

        try{


            return playerRepository.save(playerEntity);
        }catch(Exception e){
            return null;
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
                playerModel.getDisplayName(), playerModel.getPoints(), playerModel.getAvatarUrl(), playerModel.getPlayerNumber());

        return playerRepository.save(playerEntity);
    }
}
