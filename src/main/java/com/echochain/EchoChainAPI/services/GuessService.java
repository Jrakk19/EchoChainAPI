package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import com.echochain.EchoChainAPI.data.entities.GuessEntity;
import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import com.echochain.EchoChainAPI.data.repository.GuessRepository;
import com.echochain.EchoChainAPI.data.repository.PlayerRepository;
import com.echochain.EchoChainAPI.data.repository.PromptRepository;
import com.echochain.EchoChainAPI.models.GuessModel;
import com.echochain.EchoChainAPI.models.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GuessService {

    @Autowired
    private GuessRepository guessRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PromptRepository promptRepository;

    @Autowired
    private PromptService promptService;

    public GuessEntity findById(UUID id){

            Optional<GuessEntity> guessEntity = guessRepository.findById(id);

            if(guessEntity.isPresent()){
                return guessEntity.get();
            }else {
                return null;
            }
    }

    public int makeGuess(GuessModel guessModel){

        GuessEntity guessEntity = new GuessEntity(guessModel.getId(),
                guessModel.getRoomId(), guessModel.getGameIndex(), guessModel.getChainId(), guessModel.getTitle(), guessModel.getPlayerId());
    try{
        guessRepository.save(guessEntity);
        return 1;
    }catch(Exception e){
        e.printStackTrace();
        return 0;
    }

    }

    public GuessEntity findNextGuess(int gameIndex, PlayerModel player){

        int playerCount = playerRepository.countPlayersInRoom(player.getGameId());

        System.out.println("THIS SI THE PLAYER COUNT AUDIO" + playerCount);
        System.out.println("THIS IS THE PLAYER NUMBER AUDIO" + player.getPlayerNumber());

        int nextPlayerNumber;

        if(player.getPlayerNumber() == 0){
            nextPlayerNumber = playerCount - 1;
            System.out.println("THIS IS THE NEXT PLAYER NUMBER AUDIO" + nextPlayerNumber);

        }else{
            nextPlayerNumber = player.getPlayerNumber() - 1;
            System.out.println("THIS IS THE NEXT PLAYER NUMBER AUDIO" + nextPlayerNumber);

        }

        UUID targetPlayer = playerRepository.findPlayerByPlayerNumber(player.getGameId(), nextPlayerNumber);
        System.out.println("THIS IS THE TARGET PLAYER" + targetPlayer);


        try{
            if(gameIndex == 0){
                PromptEntity promptEntity = promptRepository.findByPlayerId(targetPlayer);

                return promptService.promptToGuess(promptEntity);

            }
            GuessEntity guessEntity = guessRepository.findNextGuess(gameIndex,targetPlayer );

            return guessEntity;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
