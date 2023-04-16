package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.PlayerEntity;
import com.echochain.EchoChainAPI.data.entities.PromptEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PromptRepository extends CrudRepository<PromptEntity, UUID> {

    @Query("SELECT COUNT(*) FROM prompts WHERE prompts.game_stage = :gameIndex AND prompts.room_id = :roomId")
    int countPromptsForGameIndex(@Param("gameIndex") int gameIndex, @Param("roomId") UUID roomId);

    @Query("SELECT * FROM prompts WHERE prompts.player_id = :playerId")
    PromptEntity findByPlayerId(@Param("playerId") UUID playerId);
}
