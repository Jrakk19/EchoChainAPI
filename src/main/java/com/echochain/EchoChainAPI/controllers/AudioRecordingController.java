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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.echochain.EchoChainAPI.configurations.AWSConfig;

@RestController
@RequestMapping("/recording")
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
    @PostMapping("")
    public int createRoom(@RequestParam("file")MultipartFile audioFile) throws IOException {
        System.out.println(audioFile.getOriginalFilename());
        System.out.println(awsConfig.getS3().getBucketName());

        try{
            AmazonS3 s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withRegion(awsConfig.getRegion())
                    .build();

            s3Client.putObject(awsConfig.getS3().getBucketName(), "testing_audio_wav",
                    audioFile.getInputStream(), new ObjectMetadata());
        }catch(Exception e){
            System.out.println(e);
        }

        return 1;
        //return service.createRecordingInS3Bucket(recording);
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> getFile() throws IOException {

        try{
            AmazonS3 amazonS3 = AmazonS3ClientBuilder
                    .standard()
                    .withRegion(awsConfig.getRegion())
                    .build();

            S3Object s3Object= amazonS3.getObject(awsConfig.getS3().getBucketName(), "testing_audio");

            S3ObjectInputStream s3ObjectInputStream= s3Object.getObjectContent();

            byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);
            ByteArrayResource resource = new ByteArrayResource(bytes);

            return ResponseEntity.ok()
                    .contentLength(s3Object.getObjectMetadata().getContentLength())
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .body(resource);
        }catch(AmazonS3Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
                recordingEntity.getS3Key(), recordingEntity.getGameIndex());

        return audioModel;
    }
}
