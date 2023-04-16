package com.echochain.EchoChainAPI.data.DTO;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import com.echochain.EchoChainAPI.data.entities.GuessEntity;

import java.util.List;

public class ChainDTO {

    private String prompt;
    private List<AudioRecordingEntity> audioList;

    private List<GuessEntity> guessList;

    public ChainDTO(String prompt, List<AudioRecordingEntity> audioList, List<GuessEntity> guessList) {
        this.prompt = prompt;
        this.audioList = audioList;
        this.guessList = guessList;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<AudioRecordingEntity> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<AudioRecordingEntity> audioList) {
        this.audioList = audioList;
    }

    public List<GuessEntity> getGuessList() {
        return guessList;
    }

    public void setGuessList(List<GuessEntity> guessList) {
        this.guessList = guessList;
    }
}
