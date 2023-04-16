package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.DTO.ChainDTO;
import com.echochain.EchoChainAPI.data.entities.*;
import com.echochain.EchoChainAPI.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChainService {

    @Autowired
    private ChainRepository chainRepository;

    @Autowired
    private PromptRepository promptRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GuessRepository guessRepository;

    @Autowired
    private AudioRecordingRepository audioRecordingRepository;

    public ChainEntity createChain(UUID roomId){

        ChainEntity chainEntity = new ChainEntity(roomId);

        return chainRepository.save(chainEntity);
    }

    public int countPromptsForGameIndex(int gameIndex, UUID roomId){

        try{
            return promptRepository.countPromptsForGameIndex(gameIndex, roomId);
        }catch(Exception e){
            e.printStackTrace();
            return 100;
        }
    }

    public int countNumberOfPlayersInRoom(UUID roomId){
        try{
            return playerRepository.countPlayersInRoom(roomId);
        }catch(Exception e){
            e.printStackTrace();
            return 1000;
        }
    }

    public int countGuessesForGameIndex(int gameIndex, UUID roomId){
        try{
            return guessRepository.countNumberOfGuessesForGameIndex(gameIndex, roomId);
        }catch(Exception e){
            e.printStackTrace();
            return 1000;
        }
    }

    public int countNumberOfRecordingsForGameIndex(int gameIndex, UUID roomId){
        try{
            return audioRecordingRepository.countNumberOfRecordingsForGameIndex(gameIndex, roomId);
        }catch(Exception e){
            e.printStackTrace();
            return 1000;
        }
    }

    public int countNumberOfGuessesForGameIndex(int gameIndex, UUID roomId){
        try{
            return guessRepository.countNumberOfGuessesForGameIndex(gameIndex, roomId);
        }catch(Exception e){
            e.printStackTrace();
            return 1000;
        }

    }

    public ChainDTO getFullChain(UUID roomId, int playerNumber){

        UUID playerId = playerRepository.findPlayerByPlayerNumber(roomId, playerNumber);
        PromptEntity prompt = promptRepository.findByPlayerId(playerId);

        List<GuessEntity> guessList = guessRepository.findGuessesByChainId(prompt.getChainId());
        List<AudioRecordingEntity> audioList = audioRecordingRepository.findByChainId(prompt.getChainId());

        ChainDTO chainDTO = new ChainDTO(prompt.getTitle(), audioList, guessList);

        return chainDTO;
    }
}
