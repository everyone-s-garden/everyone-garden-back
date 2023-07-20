package com.everyonegarden.crop.cropMonth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropMonthRepository extends JpaRepository<CropMonth, Long> {

    List<CropMonth> findAllByMonths(Integer month);

}