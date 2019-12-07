package com.procedure.demo.schedulingapp.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static Date convertToDateViaInstant(LocalDate dateToConvert){
        if(dateToConvert == null){
            return null;
        }
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
    public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
