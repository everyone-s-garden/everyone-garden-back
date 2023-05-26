package com.everyonegarden.batch.mafra;

import com.everyonegarden.batch.batchStatus.BatchStatus;
import com.everyonegarden.batch.batchStatus.BatchStatusService;
import com.everyonegarden.garden.api.mafra.fetch.MafraFetchService;
import com.everyonegarden.garden.api.mafra.response.ApiMafraResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j

@RequiredArgsConstructor
@Component
public class MafraReader implements ItemReader<ApiMafraResponse> {

    private final MafraFetchService mafraFetchService;
    private final BatchStatusService batchStatusService;

    @Transactional
    @Override
    public ApiMafraResponse read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        BatchStatus mafraBatchStatus = batchStatusService.getBatchStatusByApiName("mafra");
        if (mafraBatchStatus.isBatchDone()) throw new IllegalStateException("");

        log.info("startPage {}", mafraBatchStatus.getStartPage());
        log.info("endPage {}", mafraBatchStatus.getEndPage());

        ApiMafraResponse mafraApiResponse = mafraFetchService.getMafraApiResponse(mafraBatchStatus.getStartPage(), mafraBatchStatus.getEndPage());
        mafraBatchStatus.batchProcessed(mafraBatchStatus.getEndPage());

        return mafraApiResponse;
    }

}
