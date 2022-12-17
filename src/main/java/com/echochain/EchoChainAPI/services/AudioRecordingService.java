package com.echochain.EchoChainAPI.services;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import com.echochain.EchoChainAPI.data.repository.AudioRecordingRepository;
import com.echochain.EchoChainAPI.models.AudioRecordingModel;
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

        AudioRecordingEntity recordingEntity = new AudioRecordingEntity(audioRecordingModel.getId(), audioRecordingModel.getPlayerId(),
                audioRecordingModel.getS3Key(), audioRecordingModel.getGameIndex());

        try{
            audioRecordingRepository.save(recordingEntity);

            return 1;
        }catch(Exception e){
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

        AudioRecordingEntity recordingEntity = new AudioRecordingEntity(recordingModel.getId(), recordingModel.getPlayerId(), recordingModel.getS3Key(), recordingModel.getGameIndex());

        return audioRecordingRepository.save(recordingEntity);
    }
}
