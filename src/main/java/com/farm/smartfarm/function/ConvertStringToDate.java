package com.farm.smartfarm.function;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ConvertStringToDate {
    public static LocalDate convertString (String dateFormat) {
        String dateSplit = dateFormat.split("T")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateSplit, formatter);
        return date;
    }
}
