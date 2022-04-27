package Capstone.MonitoringSystem.web.Stock;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stocks")
    public String stockList(@ModelAttribute StockSearch stockSearch, Model model) {
        List<Stock> stocks = stockService.findStocksBySearch(stockSearch);
        model.addAttribute("stocks", stocks);
        return "listPage";
    }
}
