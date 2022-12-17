package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlayerRepository extends CrudRepository<PlayerEntity, UUID> {
}

