package com.echochain.EchoChainAPI.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "prompts")
public class PromptEntity {

    @Id
    private UUID id;
    @Column("title")
    private String title;

    public PromptEntity(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public PromptEntity() {
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
