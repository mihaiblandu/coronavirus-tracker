package io.javabrains.coronavirustracker.resources;


import io.javabrains.coronavirustracker.models.Point;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import io.javabrains.coronavirustracker.services.CoronaVirusDataServiceDeath;
import io.javabrains.coronavirustracker.services.CoronaVirusDataServiceRecover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bar")
public class DataPoints {

    @Autowired
    private CoronaVirusDataServiceDeath coronaVirusDataServiceDeath;

    @Autowired
    private CoronaVirusDataServiceRecover coronaVirusDataServiceRecover;

    @Autowired
    private CoronaVirusDataService virusDataService;

    @GetMapping("/deaths")
    public ResponseEntity<List<Point>> getDeaths(){
        return new ResponseEntity(coronaVirusDataServiceDeath.getAllStats().stream().map(a->{
           Point point = new Point();
           point.setY(a.getLatestTotalCases());
           point.setLabel(a.getState() + " " + a.getCountry());
            return point;
        }).collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/recovers")
    public ResponseEntity<List<Point>> getRecovers(){
        return new ResponseEntity(coronaVirusDataServiceRecover.getAllStats().stream().map(a->{
            Point point = new Point();
            point.setY(a.getLatestTotalCases());
            point.setLabel(a.getState() + " " + a.getCountry());
            return point;
        }).collect(Collectors.toList()),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Point>> getAll(){
        return new ResponseEntity(virusDataService.getAllStats().stream().map(a->{
            Point point = new Point();
            point.setY(a.getLatestTotalCases());
            point.setLabel(a.getState() + " " + a.getCountry());
            return point;
        }).collect(Collectors.toList()),HttpStatus.OK);
    }
}
