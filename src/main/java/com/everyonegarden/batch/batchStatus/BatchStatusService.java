package com.everyonegarden.batch.batchStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BatchStatusService {

    private final BatchStatusRepository batchStatusRepository;

    public BatchStatus getBatchStatusByApiName(String apiName) {
        return batchStatusRepository.getBatchStatusByApiName(apiName)
                .orElseThrow(() -> new IllegalStateException(""));
    }

}
