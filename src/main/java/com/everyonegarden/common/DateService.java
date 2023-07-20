package com.everyonegarden.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DateService {

    public static LocalDate getDateFromString(String date) {
        if (date == null) return null;

        List<Integer> dateSplit = Arrays.stream(date.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        try {
            return LocalDate.of(
                    dateSplit.get(0),
                    dateSplit.get(1),
                    dateSplit.get(2)
            );
        } catch (DateTimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "useStartDate의 형식이 올바르지 않아요. 년, 월, 일은 온점(.)으로 구분되야 하고, 생략할 수 없어요. " +
                    "예) 2023.05.01 (가능), 2023.5.1 (가능), 2023.05 (불가능), 23.05.02 (불가능), 2023.05 (불가능)");
        }

    }

    public static LocalDateTime getDateTimeFromString(String date) {
        if (date == null) return null;

        List<Integer> dateSplit = Arrays.stream(date.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        try {
            return LocalDate.of(
                    dateSplit.get(0),
                    dateSplit.get(1),
                    dateSplit.get(2)
            ).atStartOfDay();
        } catch (DateTimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "useStartDate의 형식이 올바르지 않아요. 년, 월, 일은 온점(.)으로 구분되야 하고, 생략할 수 없어요. " +
                    "예) 2023.05.01 (가능), 2023.5.1 (가능), 2023.05 (불가능), 23.05.02 (불가능), 2023.05 (불가능)");
        }

    }

}