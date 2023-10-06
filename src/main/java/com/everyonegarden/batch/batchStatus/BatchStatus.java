package com.everyonegarden.batch.batchStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@Table(name = "BATCH_STATUS")
@Entity
public class BatchStatus {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "api_name")
    private String apiName;

    @Column(name = "size")
    private Integer size;

    @Column(name = "current_page")
    private Integer currentPage;

    @Column(name = "batch_request_page")
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
