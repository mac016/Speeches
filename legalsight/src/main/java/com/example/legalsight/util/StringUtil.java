package com.example.legalsight.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class StringUtil {

    public static Date stringToDate(String stringDate) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        Date date = new Date();
        try {
            date = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static LocalDate stringToLocalDate(String stringDate) {
        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return date;
    }
}
