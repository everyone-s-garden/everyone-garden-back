package com.everyonegarden.batch.mafra;


import com.everyonegarden.garden.api.mafra.response.ApiMafraResponse;
import com.everyonegarden.garden.model.Garden;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class MafraJob {

    public static final String JOB_NAME = "mafraFetchJob";
    private static final String STEP_NAME = "mafraFetchStep";

    private final MafraReader mafraReader;
    private final MafraProcessor mafraProcessor;
    private final MafraWriter mafraWriter;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get(STEP_NAME)
                .<ApiMafraResponse, List<Garden>>chunk(2)
                .reader(mafraReader)
                .processor(mafraProcessor)
                .writer(mafraWriter)
                .build();
    }

}
