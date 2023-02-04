package com.echochain.EchoChainAPI.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import com.echochain.EchoChainAPI.models.AudioRecordingModel;
import com.echochain.EchoChainAPI.services.AudioRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.echochain.EchoChainAPI.configurations.AWSConfig;

@RestController
@RequestMapping("/recording")
@CrossOrigin
public class AudioRecordingController {

    @Autowired
    AWSConfig awsConfig;
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
                    recordingEntity.getS3Key(), recordingEntity.getGameIndex(), recordingEntity.getRoomId()));
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
                recordingEntity.getS3Key(), recordingEntity.getGameIndex(), recordingEntity.getRoomId());

        return recordingModel;
    }

    /**
     * Create a recording that gets put in the s3 bucket and logged in the db
     * @param recording - Model of what will be created
     * @return 1 for a successful post oro 0 for unsuccessful
     */
    @PostMapping("")
    public int uploadFile(@RequestParam("file")MultipartFile audioFile,
                          @RequestParam("playerId") UUID player_id,
                          @RequestParam("gameIndex") int gameIndex,
                          @RequestParam("roomId") UUID roomId)  throws IOException {

        System.out.println(player_id);
        System.out.println(gameIndex);
        UUID newS3Key = UUID.randomUUID();
        AudioRecordingModel audioModel = new AudioRecordingModel(UUID.randomUUID(), player_id, newS3Key, gameIndex, roomId);

        System.out.println(audioModel.getId());

        try{
            service.createRecordingInS3Bucket(audioModel);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        System.out.println(audioFile.getOriginalFilename());
        System.out.println(awsConfig.getS3().getBucketName());
        //Upload a tracking object to DB to Audio Recordings.
        //Use the ID of that object as the file name.
        //add user_id to API params,
        try{
            AmazonS3 s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withRegion("us-west-1")
                    .build();

            s3Client.putObject(awsConfig.getS3().getBucketName(), newS3Key.toString(),
                    audioFile.getInputStream(), new ObjectMetadata());

            System.out.println("Added to the S3");
        }catch(Exception e){
            System.out.println(e);
        }

        return 1;
        //return service.createRecordingInS3Bucket(recording);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> getFile(@RequestParam UUID audioId) throws IOException {

        AudioRecordingEntity audioModel = service.findById(audioId);

        System.out.println("We are here" + audioModel.getS3Key());
        try{
            AmazonS3 amazonS3 = AmazonS3ClientBuilder
                    .standard()
                    .withRegion("us-west-1")
                    .build();

            S3Object s3Object= amazonS3.getObject(awsConfig.getS3().getBucketName(), audioModel.getS3Key().toString());

            System.out.println(s3Object);
            InputStream audioStream= s3Object.getObjectContent();

            System.out.println(audioStream.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + "testing_audio_M4A");

            return new ResponseEntity<>(new InputStreamResource(audioStream), headers, HttpStatus.OK);
        }catch(AmazonS3Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
                recordingEntity.getS3Key(), recordingEntity.getGameIndex(), recordingEntity.getRoomId());

        return audioModel;
    }
}
