package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoomRepository extends CrudRepository<RoomEntity, UUID> {
}
