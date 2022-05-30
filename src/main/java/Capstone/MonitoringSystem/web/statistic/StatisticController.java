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

        List<Stock> yearStocks = stocks.stream()
                .filter(s -> (s.getStockedDate().getYear() == 2022))
                .collect(Collectors.toList());

        //월별 매출
        List<Integer> monthTotal = getMonthTotalList(yearReleases);

        //월별 순이익
        List<Integer> monthProfit = getMonthProfit(yearStocks, monthTotal);

        //월별 입고
        List<Double> monthStocks = getMonthStocks(yearStocks);

        //월별 출고
        List<Double> monthReleases = getMonthReleases(yearReleases);

        model.addAttribute("monthTotal", monthTotal);
        model.addAttribute("monthProfit", monthProfit);
        model.addAttribute("monthStocks", monthStocks);
        model.addAttribute("monthReleases", monthReleases);

        return "statisticsPage";
    }

    private List<Double> getMonthStocks(List<Stock> yearStocks) {
        List<Double> monthStock = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            double totalStock = 0.0;
            for (Stock stock : yearStocks) {
                LocalDate stockDate = stock.getStockedDate();
                if (stockDate.getMonthValue() == i) {
                    totalStock += stock.getQuantity();
                }
            }
            monthStock.add(totalStock);
        }
        return monthStock;
    }

    private List<Double> getMonthReleases(List<Release> yearRelease) {
        List<Double> monthReleases = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            double totalStock = 0.0;
            for (Release release : yearRelease) {
                LocalDate releasedDate = release.getReleasedDate();
                if (releasedDate.getMonthValue() == i) {
                    totalStock += release.getQuantity();
                }
            }
            monthReleases.add(totalStock);
        }
        return monthReleases;
    }

    private List<Integer> getMonthTotalList(List<Release> yearReleases) {
        List<Integer> monthTotal = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            double total = 0.0;
            for (Release release : yearReleases) {
                LocalDate releasedDate = release.getReleasedDate();
                if (releasedDate.getMonthValue() == i) {
                    total += release.getPrice();
                }
            }
            monthTotal.add((int)total);
        }
        return monthTotal;
    }

    private List<Integer> getMonthProfit(List<Stock> yearStocks, List<Integer> monthTotal) {
        List<Integer> monthProfit = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            double profit = monthTotal.get(i);
            for (Stock stock : yearStocks) {
                LocalDate date = stock.getStockedDate();
                if (date.getMonthValue() == i) {
                    profit -= stock.getPrice();
                }
            }
            monthProfit.add((int)profit);
        }
        return monthProfit;
    }
}
