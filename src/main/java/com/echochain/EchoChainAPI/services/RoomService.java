package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.data.repository.PlayerRepository;
import com.echochain.EchoChainAPI.data.repository.RoomRepository;
import com.echochain.EchoChainAPI.models.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public List<RoomEntity> findAll(){

        List<RoomEntity> rooms = new ArrayList<>();

        Iterable<RoomEntity> roomEntities = roomRepository.findAll();

        roomEntities.forEach(rooms::add);

        return rooms;
    }

    public RoomEntity findById(UUID id){

        Optional<RoomEntity> roomEntity = roomRepository.findById(id);

        if(roomEntity.isPresent()){
            return roomEntity.get();
        }else{
            return null;
        }
    }

    public RoomEntity createRoom(RoomModel roomModel){

        RoomEntity roomEntity = new RoomEntity(roomModel.getCode(),
                roomModel.getGameName(), roomModel.getGameState());

        try{
            roomRepository.save(roomEntity);
            return roomRepository.findByRoomId(roomEntity.getCode());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public RoomEntity findByRoomCode(String roomCode){
        return roomRepository.findByRoomId(roomCode);
    }
    public void initializeGame(UUID roomId){

        try{
            List<PlayerEntity> players = playerRepository.findPlayersInRoom(roomId);

            List<PlayerEntity> updatedPlayers = assignPlayerNumbers(players);

            updatedPlayers.forEach(playerEntity -> {
                playerRepository.save(playerEntity);
            });
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public int deleteRoom(UUID id){

        try{
            roomRepository.deleteById(id);
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    public RoomEntity updateRoom(RoomModel roomModel){

        RoomEntity roomEntity = new RoomEntity(roomModel.getId(), roomModel.getCode(),
                roomModel.getGameName(), roomModel.getGameState());

        return roomRepository.save(roomEntity);
    }

    private List<PlayerEntity> assignPlayerNumbers(List<PlayerEntity> players){
        int size = players.size();
        List<Integer> numbers = new ArrayList<>();
        Set<Integer> usedNumbers = new HashSet<>();

        for (int i = 0; i < size; i++) {
            int num;
            do {
                num = (int) (Math.random() * size);
            } while (usedNumbers.contains(num));
            usedNumbers.add(num);
            numbers.add(num);
        }

        for (int i = 0; i < size; i++) {
            players.get(i).setPlayerNumber(numbers.get(i));
        }

        return players;
    }
}
