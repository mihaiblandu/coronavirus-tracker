package io.javabrains.coronavirustracker.services;

import io.javabrains.coronavirustracker.models.AgregateStats;
import io.javabrains.coronavirustracker.models.DayInfo;
import io.javabrains.coronavirustracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class CoronaVirusAllData {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @Autowired
    CoronaVirusDataServiceDeath coronaVirusDataServiceDeath;
    @Autowired
    CoronaVirusDataServiceRecover coronaVirusDataServiceRecover;

    public List<AgregateStats> getAgregateData(){
        List<LocationStats> agregateCasesList = coronaVirusDataService.getAllStats();
        List<LocationStats> agregateDeathsList = coronaVirusDataServiceDeath.getAllStats();
        List<LocationStats> agregateRecoversList = coronaVirusDataServiceRecover.getAllStats();

        List<AgregateStats> aggrs = new ArrayList<>();

        int i = 0;
        for(LocationStats location : agregateCasesList){

            List cases = location.getData();
            List deaths = agregateDeathsList.get(i).getData();
            List recovers = agregateRecoversList.get(i).getData();

            AgregateStats aggr = new AgregateStats();

            aggr.setCountry(location.getCountry());
            aggr.setState(location.getState());
            aggr.setLat(location.getLat());
            aggr.setLng(location.getLng());
            aggr.setLatestTotalCases(location.getLatestTotalCases());
            aggr.setDiffFromPrevDay(location.getDiffFromPrevDay());
            aggr.setLatestTotalDeaths(agregateDeathsList.get(i).getLatestTotalCases());
            aggr.setDiffFromPrevDayDeaths(agregateDeathsList.get(i).getDiffFromPrevDay());
            aggr.setLatestTotalRecovers(agregateRecoversList.get(i).getLatestTotalCases());
            aggr.setDiffFromPrevDay(agregateRecoversList.get(i).getDiffFromPrevDay());
            int j = 0;
            List<DayInfo> map = new ArrayList<>();
            for (Object str : cases){

                deaths.get(j);
                recovers.get(j);

                String d[] = str.toString().split("=");
                DayInfo dayInfo = new DayInfo();
                String t[] = d[0].split("/");
                dayInfo.setDate(LocalDate.of(Integer.valueOf(t[2]) + 2000,Integer.valueOf(t[0]),Integer.valueOf(t[1])));
                dayInfo.setCases(Integer.valueOf(d[1]));
                dayInfo.setDeaths(Integer.valueOf(deaths.get(j).toString().split("=")[1]));
                dayInfo.setRecovers(Integer.valueOf(recovers.get(j).toString().split("=")[1]));
                map.add(dayInfo);
                j++;
            }
            aggr.setData(map);

            i++;
            aggrs.add(aggr);
        }
        return aggrs;
    }
}
