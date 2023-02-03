package com.echochain.EchoChainAPI.data.repository;

import com.echochain.EchoChainAPI.data.entities.AudioRecordingEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface AudioRecordingRepository extends CrudRepository<AudioRecordingEntity, UUID> {

}
