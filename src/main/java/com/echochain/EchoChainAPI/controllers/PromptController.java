package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.data.DTO.CreatePromptRequest;
import com.echochain.EchoChainAPI.data.entities.ChainEntity;
import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.models.PromptModel;
import com.echochain.EchoChainAPI.services.ChainService;
import com.echochain.EchoChainAPI.services.PromptService;
import com.echochain.EchoChainAPI.services.RoomService;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/prompts")
public class PromptController {

    @Autowired
    PromptService service;

    @Autowired
    ChainService chainService;

    @Autowired
    RoomService roomService;

    Pusher pusher = new Pusher("1538175", "d2348725df402f73b423", "74464c794ccc28926f11");

    /**
     * Get all prompts from the DB
     * @return - A list of prompt Models
     */
    @GetMapping("/get")
    public List<PromptModel> getPrompt(){
        List<PromptEntity> promptEntityList = service.findPrompts();

        List<PromptModel> promptModels = new ArrayList<>();

        promptEntityList.forEach(prompt -> {
            promptModels.add(new PromptModel(prompt.getId(), prompt.getTitle(), prompt.getRoomId(), prompt.getGameIndex(), prompt.getChainId()));
        });

        return promptModels;
    }

    @PostMapping("/original-prompt")
    public void postOriginalPrompt(@RequestBody CreatePromptRequest prompt){
        System.out.println("PLEEEEEEASE");

        ChainEntity chainEntity = chainService.createChain(prompt.getRoomId());

        service.create(prompt.requestToModel(prompt, chainEntity.getId()));

        int numberOfPrompts = chainService.countPromptsForGameIndex(prompt.getGameIndex(), prompt.getRoomId());
        System.out.println("THIS IS THE NUMBER" + numberOfPrompts);

        int numberOfPlayers = chainService.countNumberOfPlayersInRoom(prompt.getRoomId());
        System.out.println("HERE NUMBER OF PLAYERS" + numberOfPlayers);

        RoomEntity roomEntity = roomService.findById(prompt.getRoomId());

        if(numberOfPrompts == numberOfPlayers){
            System.out.println("WE MADE IT HERE ");
            pusher.setCluster("us3");
            pusher.setEncrypted(true);
            pusher.trigger(roomEntity.getCode().toString(), "guess", Collections.singletonMap("message", "Hello World"));
        }


    }

}
