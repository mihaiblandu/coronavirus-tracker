package io.javabrains.coronavirustracker.models;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class AgregateStats {
     String state;
     String country;
     int latestTotalCases;
     int diffFromPrevDay;
     int latestTotalDeaths;
     int diffFromPrevDayDeaths;
     int latestTotalRecovers;
     int diffFromPrevDayRecovers;
     float lat;
     float lng;

    private List<DayInfo> data;
}
