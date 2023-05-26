package com.everyonegarden.batch.batchStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchStatusRepository extends JpaRepository<BatchStatus, Long> {

    Optional<BatchStatus> getBatchStatusByApiName(String apiName);

}
