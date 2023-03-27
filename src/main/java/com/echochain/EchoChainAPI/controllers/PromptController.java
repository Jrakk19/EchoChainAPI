package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.data.DTO.CreatePromptRequest;
import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import com.echochain.EchoChainAPI.models.PromptModel;
import com.echochain.EchoChainAPI.services.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prompts")
public class PromptController {

    @Autowired
    PromptService service;

    /**
     * Get all prompts from the DB
     * @return - A list of prompt Models
     */
    @GetMapping("/get")
    public List<PromptModel> getPrompt(){
        List<PromptEntity> promptEntityList = service.findPrompts();

        List<PromptModel> promptModels = new ArrayList<>();

        promptEntityList.forEach(prompt -> {
            promptModels.add(new PromptModel(prompt.getId(), prompt.getTitle(), prompt.getRoomId(), prompt.getGameIndex()));
        });

        return promptModels;
    }

    @PostMapping("/original-prompt")
    public void postOriginalPrompt(@RequestBody CreatePromptRequest prompt){
        System.out.println("PLEEEEEEASE");

        service.create(prompt.requestToModel(prompt));
    }

}
