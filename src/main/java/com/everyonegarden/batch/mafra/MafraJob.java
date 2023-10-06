package com.everyonegarden.batch.mafra;

import com.everyonegarden.garden.garden.Garden;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;


@RequiredArgsConstructor
@Configuration
public class MafraJob {

    private static final String JOB_NAME = "mafraFetchJob";
    private static final String STEP_NAME = "mafraFetchStep";

    private final MafraReader mafraReader;
    private final MafraProcessor mafraProcessor;
    private final MafraWriter mafraWriter;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job mafraFetchJob() {
        return new JobBuilder(JOB_NAME,jobRepository)
                .start(mafraFetchStep())
                .build();
    }

    @Bean
    public Step mafraFetchStep() {
        return new StepBuilder(STEP_NAME,jobRepository)
                .<ApiMafraResponse, List<Garden>>chunk(2,transactionManager)
                .reader(mafraReader)
                .processor(mafraProcessor)
                .writer(mafraItemWriter())
                .build();
    }

    @Bean
    public ItemWriter<List<Garden>> mafraItemWriter() {
        return mafraWriter;
    }
}
