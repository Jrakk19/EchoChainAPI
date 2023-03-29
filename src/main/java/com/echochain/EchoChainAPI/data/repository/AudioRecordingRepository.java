package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AudioRecordingRepository extends CrudRepository<AudioRecordingEntity, UUID> {

    @Query("SELECT COUNT(*) FROM audio_recordings WHERE audio_recordings.game_index = :gameIndex AND audio_recordings.room_id = :roomId")
    int countNumberOfRecordingsForGameIndex(@Param("gameIndex") int gameIndex, @Param("roomId") UUID roomId);

    @Query("SELECT * FROM audio_recordings where audio_recordings.player_id = :playerId AND audio_recordings.game_index = :gameIndex")
    AudioRecordingEntity findNextAudio(@Param("gameIndex") int gameIndex, @Param("playerId") UUID playerId);
}
