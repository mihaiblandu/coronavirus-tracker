package io.javabrains.coronavirustracker.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class DayInfo {
    private LocalDate date ;
    private int cases;
    private int deaths;
    private int recovers;
}
