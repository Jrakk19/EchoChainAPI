package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import com.echochain.EchoChainAPI.data.repository.AudioRecordingRepository;
import com.echochain.EchoChainAPI.data.repository.PlayerRepository;
import com.echochain.EchoChainAPI.data.repository.RoomRepository;
import com.echochain.EchoChainAPI.models.AudioRecordingModel;
import com.echochain.EchoChainAPI.models.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AudioRecordingService {

    @Autowired
    private AudioRecordingRepository audioRecordingRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public List<AudioRecordingEntity> findAll() {

        List<AudioRecordingEntity> audios = new ArrayList<>();

        Iterable<AudioRecordingEntity> audioRecordings = audioRecordingRepository.findAll();

        audioRecordings.forEach(audios::add);

        return audios;
    }

    public AudioRecordingEntity findById(UUID id){

        Optional<AudioRecordingEntity> audioRecordingEntity = audioRecordingRepository.findById(id);

        if(audioRecordingEntity.isPresent()){
            return audioRecordingEntity.get();
        }else{
            return null;
        }
    }

    public int createRecordingInS3Bucket(AudioRecordingModel audioRecordingModel) {

        AudioRecordingEntity recordingEntity = new AudioRecordingEntity(audioRecordingModel.getPlayerId(),
                audioRecordingModel.getS3Key(), audioRecordingModel.getGameIndex(), audioRecordingModel.getRoomId(), audioRecordingModel.getChainId());

        try{
            audioRecordingRepository.save(recordingEntity);

            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteAudioRecording(UUID id){
        try{
            audioRecordingRepository.deleteById(id);
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    public AudioRecordingEntity updateAudioRecording(AudioRecordingModel recordingModel){

        AudioRecordingEntity recordingEntity = new AudioRecordingEntity(recordingModel.getId(), recordingModel.getPlayerId(), recordingModel.getS3Key(), recordingModel.getGameIndex(), recordingModel.getRoomId(), recordingModel.getChainId());

        return audioRecordingRepository.save(recordingEntity);
    }

    public AudioRecordingEntity findNextAudio(int gameIndex, PlayerModel player){

        int playerCount = playerRepository.countPlayersInRoom(player.getGameId());

        int nextPlayerNumber;

        System.out.println("THIS SI THE PLAYER COUNT AUDIO" + playerCount);
        System.out.println("THIS IS THE PLAYER NUMBER AUDIO" + player.getPlayerNumber());
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
            AudioRecordingEntity audioRecordingEntity = audioRecordingRepository.findNextAudio(gameIndex,targetPlayer );

            return audioRecordingEntity;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
