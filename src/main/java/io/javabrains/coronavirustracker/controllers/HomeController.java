package io.javabrains.coronavirustracker.controllers;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusAllData;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import io.javabrains.coronavirustracker.services.CoronaVirusDataServiceDeath;
import io.javabrains.coronavirustracker.services.CoronaVirusDataServiceRecover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;
    @Autowired
    private CoronaVirusAllData coronaVirusAllData;
    @Autowired
    private CoronaVirusDataServiceDeath coronaVirusDataServiceDeath;
    @Autowired
    private CoronaVirusDataServiceRecover coronaVirusDataServiceRecover;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService
                .getAllStats()
                .stream()
                .sorted(Comparator.comparing(LocationStats::getCountry))
                .collect(Collectors.toList());
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("aggregateData", coronaVirusAllData.getAgregateData());
        model.addAttribute("totalDeaths",coronaVirusDataServiceDeath.getAllStats().stream().map(el->el.getLatestTotalCases()).reduce(0,(a,b)->a+b));
        model.addAttribute("totalDiffDeaths",coronaVirusDataServiceDeath.getAllStats().stream().map(el->el.getDiffFromPrevDay()).reduce(0,(a,b)->a+b));
        model.addAttribute("totalRecovers",coronaVirusDataServiceRecover.getAllStats().stream().map(el->el.getLatestTotalCases()).reduce(0,(a,b)->a+b));
        model.addAttribute("totalDiffRecovers",coronaVirusDataServiceRecover.getAllStats().stream().map(el->el.getDiffFromPrevDay()).reduce(0,(a,b)->a+b));
        return "home";
    }


    @GetMapping("/graph")
        public String getGraph()
        {
            return "graph";
        }
}
