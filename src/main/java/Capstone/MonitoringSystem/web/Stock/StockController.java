package Capstone.MonitoringSystem.web.Stock;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import Capstone.MonitoringSystem.domain.Stock.StockUpdateForm;
import Capstone.MonitoringSystem.domain.Stock.stockrepository.StockRepository;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;
    private final StorageRepository sr;

    @GetMapping("/stocks")
    public String stockList(@ModelAttribute("search") StockSearch stockSearch, Model model) {
        List<Stock> stocks = stockService.findStocksBySearch(stockSearch);
        model.addAttribute("stocks", stocks);
        return "listPage";
    }

    @GetMapping("/stocks/update/{stockId}")
    public String stockUpdateForm(@PathVariable Long stockId, Model model) {

        Stock stock = stockService.findStock(stockId);
        StockUpdateForm stockUpdateForm = new StockUpdateForm(stockId, stock.getName(), stock.getDryingPlace(), stock.getQuantity(), stock.getPrice(),
                stock.getStockedDate(), stock.getYield(), stock.getStorage().getId());
        model.addAttribute("stock", stockUpdateForm);

        return "stockModifyPage";
    }

    @PostMapping("/stocks/update")
    public String stockUpdate(@Validated @ModelAttribute("stock") StockUpdateForm form,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "stockModifyPage";
        }

        stockService.updateStock(form);

        return "redirect:/stocks";
    }
}
