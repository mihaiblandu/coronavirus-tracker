package io.javabrains.coronavirustracker.models;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;
    private float lat;
    private float lng;
    private List data;
}
