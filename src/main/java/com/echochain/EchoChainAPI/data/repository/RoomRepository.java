package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.UUID;

public interface RoomRepository extends CrudRepository<RoomEntity, UUID> {
    @Query("SELECT * FROM room WHERE room.code = :code")
    RoomEntity findByRoomId(@Param("code") String code);
}
