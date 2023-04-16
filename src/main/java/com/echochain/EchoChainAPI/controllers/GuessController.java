package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.data.DTO.RequestNextStep;
import com.echochain.EchoChainAPI.data.entities.GuessEntity;
import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.models.GuessModel;
import com.echochain.EchoChainAPI.models.PlayerModel;
import com.echochain.EchoChainAPI.services.*;
import com.google.gson.Gson;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/guess")
@CrossOrigin
public class GuessController {

    @Autowired
     GuessService service;

    @Autowired
    PromptService promptService;

    @Autowired
    ChainService chainService;

    @Autowired
    RoomService roomService;

    @Autowired
    PlayerService playerService;

    Pusher pusher = new Pusher("1538175", "d2348725df402f73b423", "74464c794ccc28926f11");

    /**
     * find a guess by its Id
     * @param id - Id of the guess you want to find
     * @return - the guess found by its Id
     */
    @GetMapping("/{id}")
    public GuessModel findGuessById(@PathVariable UUID id){

        GuessEntity guessEntity = service.findById(id);

        GuessModel guessModel = new GuessModel(guessEntity.getId(),
                guessEntity.getRoomId(), guessEntity.getGameIndex(), guessEntity.getChainId(), guessEntity.getTitle(), guessEntity.getPlayerId());

        return guessModel;
    }

    /**
     * Make a guess with the body given by the user
     * @param guess - Guess Model with info to put in db
     * @return - returns 1 for success and 0 for unsuccessful post
     */
    @PostMapping("/create")
    public void makeGuess(@RequestBody GuessModel guess){
         service.makeGuess(guess);

         int numberOfGuesses = chainService.countGuessesForGameIndex(guess.getGameIndex(), guess.getRoomId());
         int numberOfPlayers = chainService.countNumberOfPlayersInRoom(guess.getRoomId());
        RoomEntity roomEntity = roomService.findById(guess.getRoomId());

        PlayerEntity playerEntity = playerService.findById(guess.getPlayerId());
        PlayerModel playerModel = new PlayerModel(playerEntity.getId(), playerEntity.getGameId(), playerEntity.getDisplayName(), playerEntity.getPoints(), playerEntity.getAvatarUrl(), playerEntity.getPlayerNumber());

        GuessEntity guessEntity = service.findNextGuess(guess.getGameIndex(), playerModel);
        PromptEntity promptEntity = promptService.findByPlayerId(playerEntity.getId());

        System.out.println("THIS IS THE COMPARISON" + guessEntity.getChainId().equals(promptEntity.getChainId()));
        if(numberOfGuesses == numberOfPlayers){
            if(guessEntity.getChainId().equals(promptEntity.getChainId())&& guessEntity.getGameIndex() != 0)
            {
                pusher.setCluster("us3");
                pusher.setEncrypted(true);
                pusher.trigger(roomEntity.getCode().toString(), "end-game", Collections.singletonMap("message", "Hello World"));
            }
            else {
                System.out.println("WE MADE IT HERE ");
                pusher.setCluster("us3");
                pusher.setEncrypted(true);
                pusher.trigger(roomEntity.getCode().toString(), "record", Collections.singletonMap("message", "Hello World"));
            }

         }
    }

    @GetMapping("/next-guess")
    public GuessEntity getNextGuess(@RequestParam("request") String requestJson){
        Gson gson = new Gson();
        RequestNextStep request = gson.fromJson(requestJson, RequestNextStep.class);


        GuessEntity guessEntity = service.findNextGuess(request.getGameIndex(), request.getPlayer());

        return guessEntity;
    }
}
