package io.javabrains.coronavirustracker.resources;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusAllData;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import io.javabrains.coronavirustracker.services.CoronaVirusDataServiceDeath;
import io.javabrains.coronavirustracker.services.CoronaVirusDataServiceRecover;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class Api {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;
    @Autowired
    private CoronaVirusDataServiceDeath coronaVirusDataServiceDeath;

    @Autowired
    private CoronaVirusDataServiceRecover coronaVirusDataServiceRecover;

    @Autowired
    private CoronaVirusAllData coronaVirusAllData;

    @GetMapping("/allCases")
    public ResponseEntity<LocationStats> allData() {

        List<LocationStats> allStats = coronaVirusDataService
                .getAllStats()
                .stream()
                .sorted(Comparator.comparing(LocationStats::getCountry))
                .collect(Collectors.toList());

        return new ResponseEntity(allStats,HttpStatus.OK);
    }


    @GetMapping("/countryCases")
    public ResponseEntity  countryData(@RequestParam String country) {
        List<LocationStats> allStats = null;
            System.out.println(country);
            allStats = coronaVirusDataService
                    .getAllStats()
                    .stream().filter(e->e.getCountry().equals(country))
                    .sorted(Comparator.comparing(LocationStats::getCountry))
                    .collect(Collectors.toList());


        return new ResponseEntity(allStats ,HttpStatus.OK);
    }
    @GetMapping("/allDeaths")
    public ResponseEntity<LocationStats> allDeaths() {

        List<LocationStats> allStats = coronaVirusDataServiceDeath
                .getAllStats()
                .stream()
                .sorted(Comparator.comparing(LocationStats::getCountry))
                .collect(Collectors.toList());

        return new ResponseEntity(allStats,HttpStatus.OK);
    }
    @GetMapping("/countryDeaths")
    public ResponseEntity  countryDeaths(@RequestParam String country) {
        List<LocationStats> allStats = null;
        System.out.println(country);
        allStats = coronaVirusDataServiceDeath
                .getAllStats()
                .stream().filter(e->e.getCountry().equals(country))
                .sorted(Comparator.comparing(LocationStats::getCountry))
                .collect(Collectors.toList());


        return new ResponseEntity(allStats ,HttpStatus.OK);
    }
    @GetMapping("/allRecovers")
    public ResponseEntity<LocationStats> allRecovered() {

        List<LocationStats> allStats = coronaVirusDataServiceRecover
                .getAllStats()
                .stream()
                .sorted(Comparator.comparing(LocationStats::getCountry))
                .collect(Collectors.toList());

        return new ResponseEntity(allStats,HttpStatus.OK);
    }
    @GetMapping("/countryRecovers")
    public ResponseEntity  countryRecovers(@RequestParam String country) {
        List<LocationStats> allStats = null;
        System.out.println(country);
        allStats = coronaVirusDataServiceRecover
                .getAllStats()
                .stream().filter(e->e.getCountry().equals(country))
                .sorted(Comparator.comparing(LocationStats::getCountry))
                .collect(Collectors.toList());

        return new ResponseEntity(allStats ,HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity getData(){
        return new ResponseEntity(coronaVirusAllData.getAgregateData(),HttpStatus.OK);
    }
}
