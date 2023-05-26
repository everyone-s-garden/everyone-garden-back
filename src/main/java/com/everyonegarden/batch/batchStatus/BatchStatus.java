package com.everyonegarden.batch.batchStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class BatchStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiName;

    private Integer size;
    private Integer currentPage;
    private Integer batchRequestPage;

    public boolean isBatchDone() {
        return currentPage > batchRequestPage;
    }

    public int getStartPage() {
        return currentPage;
    }

    public int getEndPage() {
        return currentPage + size;
    }

    public void batchProcessed(int endPage) {
        if (endPage < currentPage) throw new IllegalStateException("");

        this.currentPage = endPage + size;
    }

}
