package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.GuessEntity;
import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import com.echochain.EchoChainAPI.data.repository.PromptRepository;
import com.echochain.EchoChainAPI.models.PromptModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            PromptEntity promptEntity = new PromptEntity(prompt.getTitle(), prompt.getRoomId(), prompt.getGameIndex(), prompt.getChainId(), prompt.getPlayerId());
            promptRepository.save(promptEntity);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public PromptEntity findByPlayerId(UUID playerId){
        return promptRepository.findByPlayerId(playerId);
    }

    public GuessEntity promptToGuess(PromptEntity prompt) {
        String title = prompt.getTitle();
        UUID id = prompt.getId();
        UUID roomId = prompt.getRoomId();
        int gameIndex = prompt.getGameIndex();
        UUID chainId = prompt.getChainId();
        UUID playerId = prompt.getPlayerId();

        return new GuessEntity(id, roomId, gameIndex, chainId, title, playerId);
    }
}
