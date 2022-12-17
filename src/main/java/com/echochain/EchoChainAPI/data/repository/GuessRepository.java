package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.GuessEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GuessRepository extends CrudRepository<GuessEntity, UUID> {
}
