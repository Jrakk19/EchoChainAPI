package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import com.echochain.EchoChainAPI.data.repository.PromptRepository;
import com.echochain.EchoChainAPI.models.PromptModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromptService {

    @Autowired
    private PromptRepository promptRepository;

    public List<PromptEntity> findPrompts(){

        List<PromptEntity> prompts = new ArrayList<>();

        Iterable<PromptEntity> promptEntities = promptRepository.findAll();

        promptEntities.forEach(prompts::add);

        return prompts;
    }

    public void create(PromptModel prompt){

        try{
            PromptEntity promptEntity = new PromptEntity(prompt.getTitle(), prompt.getRoomId(), prompt.getGameIndex());
            promptRepository.save(promptEntity);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
