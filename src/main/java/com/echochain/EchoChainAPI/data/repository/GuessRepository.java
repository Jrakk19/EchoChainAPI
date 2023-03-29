package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.GuessEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface GuessRepository extends CrudRepository<GuessEntity, UUID> {
    @Query("SELECT COUNT(*) FROM guesses WHERE guesses.game_index = :gameIndex AND guesses.room_id = :roomId")
    int countNumberOfGuessesForGameIndex(@Param("gameIndex") int gameIndex, @Param("roomId") UUID roomId);
}
