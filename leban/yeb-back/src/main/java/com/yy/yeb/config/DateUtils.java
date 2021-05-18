package com.yy.yeb.config;

import org.springframework.core.convert.converter.Converter;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String s) {

        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
