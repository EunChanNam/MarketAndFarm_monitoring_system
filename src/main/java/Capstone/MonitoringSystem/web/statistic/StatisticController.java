package Capstone.MonitoringSystem.web.statistic;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Release.releaseservice.ReleaseService;
import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class StatisticController {

    private final StockService stockService;
    private final ReleaseService releaseService;

    @GetMapping("/statistics")
    public String statisticPage(Model model) {

        List<Stock> stocks = stockService.findAll();
        List<Release> releases = releaseService.findAll();

        List<Release> yearReleases = releases.stream()
                .filter(r -> (r.getReleasedDate().getYear() == 2022))
                .collect(Collectors.toList());

        List<Integer> monthTotal = getMonthTotalList(yearReleases);

        Integer totalRelease = (int)yearReleases.stream()
                                                .mapToDouble(Release::getQuantity)
                                                .sum();

        Integer totalStock = totalRelease + (int) stocks.stream()
                                                        .mapToDouble(Stock::getQuantity)
                                                        .sum();

        model.addAttribute("monthTotal", monthTotal);
        model.addAttribute("totalRelease", totalRelease);
        model.addAttribute("totalStock", totalStock);

        return "statisticsPage";
    }

    private List<Integer> getMonthTotalList(List<Release> yearReleases) {
        List<Integer> monthTotal = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            double total = 0.0;
            for (Release release : yearReleases) {
                LocalDate date = release.getReleasedDate();
                if (date.getMonthValue() == i) {
                    total += release.getPrice();
                }
            }
            monthTotal.add((int)total);
        }
        return monthTotal;
    }
}
