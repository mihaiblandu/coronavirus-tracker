package io.javabrains.coronavirustracker.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Point {
    private int y;
    private String label;
}
