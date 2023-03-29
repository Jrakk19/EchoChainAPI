package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.ChainEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChainRepository extends CrudRepository<ChainEntity, UUID> {
}
