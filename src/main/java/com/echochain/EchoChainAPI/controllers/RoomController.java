package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.configurations.AWSConfig;
import com.echochain.EchoChainAPI.data.DTO.ChainDTO;
import com.echochain.EchoChainAPI.data.DTO.RequestChain;
import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.models.PlayerModel;
import com.echochain.EchoChainAPI.models.RoomModel;
import com.echochain.EchoChainAPI.services.ChainService;
import com.echochain.EchoChainAPI.services.PlayerService;
import com.echochain.EchoChainAPI.services.RoomService;
import com.google.gson.Gson;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/room")
@CrossOrigin
public class RoomController {

    @Autowired
    AWSConfig config;
    @Autowired
    RoomService service;

    @Autowired
    PlayerService playerService;

    @Autowired
    ChainService chainService;


    Pusher pusher = new Pusher("1538175", "d2348725df402f73b423", "74464c794ccc28926f11");


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
     * @return 1 for a successful post or 0 for unsuccessful
     */
    @PostMapping("/")
    public RoomEntity createRoom(){

        RoomModel newRoom = new RoomModel(generateRoomCode(), "EchoChainGame", 0);
        System.out.println("we are in create");
        return service.createRoom(newRoom);
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

    @GetMapping("/players/{roomId}")
    public List<PlayerEntity> findPlayersInGame(@PathVariable UUID roomId){
        return playerService.findPlayersInRoom(roomId);

    }

    @PostMapping("/game/initialize/{roomId}")
    public void initializeGame(@PathVariable UUID roomId){

        RoomEntity room = service.findById(roomId);


        pusher.setCluster("us3");
        pusher.setEncrypted(true);
        pusher.trigger(room.getCode().toString(), "start-game", Collections.singletonMap("message", "Hello World"));

        service.initializeGame(roomId);
    }

    @GetMapping("/code/{roomCode}")
    public RoomEntity getRoomByRoomCode(@PathVariable String roomCode){
        System.out.println("IN GET ROOM" + roomCode);
        return service.findByRoomCode(roomCode);
    }

    @GetMapping("/get-chain")
    public ChainDTO getChain(@RequestParam("request") String requestJson){
        Gson gson = new Gson();

        RequestChain request = gson.fromJson(requestJson, RequestChain.class);

        ChainDTO chainDTO = chainService.getFullChain(request.getRoomId(), request.getPlayerNumber());

        return chainDTO;
    }

    @DeleteMapping("/leave-room/{playerId}")
    public void leaveRoom(@PathVariable("playerId") UUID playerId){
        PlayerEntity playerEntity = playerService.findById(playerId);

        playerService.deletePlayer(playerId);

        int numberOfPlayers = chainService.countNumberOfPlayersInRoom(playerEntity.getGameId());

        if(numberOfPlayers == 0){
            System.out.println("We are deleting the room" + playerEntity.getGameId().toString());
            service.deleteRoom(playerEntity.getGameId());
        }

    }
    private String generateRoomCode(){
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rng = new Random();
        char[] text = new char[4];
        for (int i = 0; i < 4; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);

    }




}
