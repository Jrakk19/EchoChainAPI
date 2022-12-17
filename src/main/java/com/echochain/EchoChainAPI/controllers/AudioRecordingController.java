package com.echochain.EchoChainAPI.controllers;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import com.echochain.EchoChainAPI.models.AudioRecordingModel;
import com.echochain.EchoChainAPI.services.AudioRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class AudioRecordingController {

    @Autowired
    AudioRecordingService service;

    /**
     * Find all Audio Recordings
     * @return A list of Recordings as AudioRecordingModels
     */
    @GetMapping("/all")
    public List<AudioRecordingModel> getAudioRecordings(){

        List<AudioRecordingEntity> recordings = service.findAll();

        List<AudioRecordingModel> recordModels = new ArrayList<>();

        recordings.forEach(recordingEntity -> {
            recordModels.add(new AudioRecordingModel(recordingEntity.getId(), recordingEntity.getPlayerId(),
                    recordingEntity.getS3Key(), recordingEntity.getGameIndex()));
        });

        return recordModels;
    }

    /**
     * Find a recording by its Id
     * @param id - UUID of the recording you are looking for
     * @return - Audio Recording Model found by its Id
     */
    @GetMapping("/{id}")
    public AudioRecordingModel findRecordingById(@PathVariable UUID id){
        AudioRecordingEntity recordingEntity = service.findById(id);

        AudioRecordingModel recordingModel = new AudioRecordingModel(recordingEntity.getId(), recordingEntity.getPlayerId(),
                recordingEntity.getS3Key(), recordingEntity.getGameIndex());

        return recordingModel;
    }

    /**
     * Create a recording that gets put in the s3 bucket and logged in the db
     * @param recording - Model of what will be created
     * @return 1 for a successful post oro 0 for unsuccessful
     */
    @PostMapping("/create")
    public int createRoom(@RequestBody AudioRecordingModel recording){
        return service.createRecordingInS3Bucket(recording);
    }

    /**
     * Delete a recording by its id
     * @param id - UUID of the recording to be deleted
     * @return - 1 for successful deletion 0 for unsuccessful
     */
    @DeleteMapping("/{id}")
    public int deleteRecording(@PathVariable UUID id){
        return service.deleteAudioRecording(id);
    }

    /**
     * Update a recording in the db
     * @param id - Id of the object you want to update
     * @param recordingModel - Updated Model to update in the db
     * @return - the updated Recording
     */
    @PutMapping("/{id}")
    public AudioRecordingModel updateRecording(@PathVariable UUID id, @RequestBody AudioRecordingModel recordingModel){

        AudioRecordingEntity recordingEntity = service.updateAudioRecording(recordingModel);

        AudioRecordingModel audioModel = new AudioRecordingModel(recordingEntity.getId(), recordingEntity.getPlayerId(),
                recordingEntity.getS3Key(), recordingEntity.getGameIndex());

        return audioModel;
    }
}
