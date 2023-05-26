package com.everyonegarden.batch.mafra;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor

@Configuration
@EnableScheduling
public class MafraJobScheduler {

    private final JobLauncher jobLauncher;
    private Job job;

    private final String EVERY_HOUR = "0 0 * * * *";

    // batch that runs for every hour
    // it fetch from api (ApiMafraResponse) and convert to Garden and save to database
    @Scheduled(cron = EVERY_HOUR)
    public void run() throws Exception {
        jobLauncher.run(job, null);
    }

}