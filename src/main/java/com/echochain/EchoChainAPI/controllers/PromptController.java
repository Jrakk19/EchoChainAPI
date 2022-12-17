package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import com.echochain.EchoChainAPI.models.PromptModel;
import com.echochain.EchoChainAPI.services.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            promptModels.add(new PromptModel(prompt.getId(), prompt.getTitle()));
        });

        return promptModels;
    }

}
