package com.echochain.EchoChainAPI.models;

import java.util.UUID;

public class PromptModel {
    private UUID id;
    private String title;

    public PromptModel(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public PromptModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
