package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.data.DTO.RequestNextStep;
import com.echochain.EchoChainAPI.data.entities.GuessEntity;
import com.echochain.EchoChainAPI.models.GuessModel;
import com.echochain.EchoChainAPI.services.GuessService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/guess")
public class GuessController {

    @Autowired
     GuessService service;

    /**
     * find a guess by its Id
     * @param id - Id of the guess you want to find
     * @return - the guess found by its Id
     */
    @GetMapping("/{id}")
    public GuessModel findGuessById(@PathVariable UUID id){

        GuessEntity guessEntity = service.findById(id);

        GuessModel guessModel = new GuessModel(guessEntity.getId(), guessEntity.getGameId(),
                guessEntity.getRoomId(), guessEntity.getGameIndex(), guessEntity.getChainId());

        return guessModel;
    }

    /**
     * Make a guess with the body given by the user
     * @param guess - Guess Model with info to put in db
     * @return - returns 1 for success and 0 for unsuccessful post
     */
    @PostMapping("/create")
    public int makeGuess(@RequestBody GuessModel guess){
        return service.makeGuess(guess);
    }

    @GetMapping("/next-guess")
    public GuessEntity getNextGuess(@RequestParam("request") String requestJson){
        Gson gson = new Gson();
        RequestNextStep request = gson.fromJson(requestJson, RequestNextStep.class);

        return service.findNextGuess(request.getGameIndex(), request.getPlayer());
    }
}
