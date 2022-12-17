package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.GuessEntity;
import com.echochain.EchoChainAPI.data.repository.GuessRepository;
import com.echochain.EchoChainAPI.models.GuessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GuessService {

    @Autowired
    private GuessRepository guessRepository;

    public GuessEntity findById(UUID id){

            Optional<GuessEntity> guessEntity = guessRepository.findById(id);

            if(guessEntity.isPresent()){
                return guessEntity.get();
            }else {
                return null;
            }
    }

    public int makeGuess(GuessModel guessModel){

        GuessEntity guessEntity = new GuessEntity(guessModel.getId(), guessModel.getGameId(),
                guessModel.getRoomId(), guessModel.getGameIndex());
    try{
        guessRepository.save(guessEntity);
        return 1;
    }catch(Exception e){
        return 0;
    }

    }


}
