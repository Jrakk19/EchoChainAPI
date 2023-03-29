package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.RoomEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;


public interface PlayerRepository extends CrudRepository<PlayerEntity, UUID> {

    @Query("SELECT * FROM players where players.room_id = :roomId")
    List<PlayerEntity> findPlayersInRoom(@Param("roomId") UUID roomId);
    @Query("Select COUNT(*) FROM players WHERE players.room_id = :roomId")
    int countPlayersInRoom(@Param("roomId") UUID roomId);

    @Query("SELECT id FROM players where players.room_id = :roomId AND players.player_number = :playerNumber")
    UUID findPlayerByPlayerNumber(@Param("roomId") UUID roomId, @Param("playerNumber") int playerNumber);
}

