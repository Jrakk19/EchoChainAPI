package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.data.repository.RoomRepository;
import com.echochain.EchoChainAPI.models.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

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

    public int createRoom(RoomModel roomModel){

        RoomEntity roomEntity = new RoomEntity(roomModel.getCode(),
                roomModel.getGameName(), roomModel.getGameState());

        try{
            roomRepository.save(roomEntity);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
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
}
