package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PromptRepository extends CrudRepository<PromptEntity, UUID> {
}
