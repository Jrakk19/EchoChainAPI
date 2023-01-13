package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.configurations.AWSConfig;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.models.RoomModel;
import com.echochain.EchoChainAPI.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    AWSConfig config;
    @Autowired
    RoomService service;

    /**
     * Find all rooms in the database
     * @return List of Rooms
     */
    @GetMapping("/")
    public List<RoomModel> getRooms() {


        List<RoomEntity> rooms = service.findAll();

        List<RoomModel> roomModels = new ArrayList<>();

        rooms.forEach(roomEntity -> {
            roomModels.add(new RoomModel(roomEntity.getId(), roomEntity.getCode(),
                    roomEntity.getGameName(), roomEntity.getGameState()));
        });

        return roomModels;
    }

    /**
     * Find a Room by its Id in the path
     * @param id - the Id of the room you are looking for
     * @return a room model found by its Id
     */
    @GetMapping("/{id}")
    public RoomModel findById(@PathVariable UUID id){
         RoomEntity roomEntity = service.findById(id);

         RoomModel roomModel = new RoomModel(roomEntity.getId(), roomEntity.getCode(),
                 roomEntity.getGameName(), roomEntity.getGameState());

         return roomModel;
    }

    /**
     * Create a Room
     * @param room - The model used to create a room in the DB
     * @return 1 for a successful post or 0 for unsuccessful
     */
    @PostMapping("/")
    public int createRoom(@RequestBody RoomModel room){

        System.out.println("we are in create");
        System.out.println(room.toString());
        return service.createRoom(room);
    }

    /**
     * Delete a Room by its Id
     * @param id - Id of the Room to delete
     * @return 1 for successful deletetion 0 for unsuccessful
     */
    @DeleteMapping("/{id}")
    public int deleteRoom(@PathVariable UUID id){
        return service.deleteRoom(id);
    }

    /**
     * Update a Room by its Id
     * @param id - Id of the Room you want to update
     * @param room - Updated Room Info
     * @return The updated room model
     */
    @PutMapping("/{id}")
    public RoomModel updateRoom(@PathVariable UUID id, @RequestBody RoomModel room){

        RoomEntity roomEntity = service.updateRoom(room);

        RoomModel roomModel = new RoomModel(roomEntity.getId(), roomEntity.getCode(),
                roomEntity.getGameName(), roomEntity.getGameState());

        return roomModel;
    }
}
