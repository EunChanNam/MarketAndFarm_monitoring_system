package Capstone.MonitoringSystem.web.Stock;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @GetMapping("/stocks")
    public String stockList(@ModelAttribute("search") StockSearch stockSearch, Model model) {
        List<Stock> stocks = stockService.findStocksBySearch(stockSearch);
        model.addAttribute("stocks", stocks);
        return "listPage";
    }

    @GetMapping("/stocks/new")
    public String stockInputForm(@ModelAttribute("form") StockInputForm form) {
        return "inputListUpload";
    }

    @PostMapping("/stocks/new")
    public String stockInput(@Validated @ModelAttribute("form") StockInputForm form,
                             BindingResult bindingResult) {
        //todo Validation
        if (bindingResult.hasErrors()) {
            return "inputListUpload";
        }

        stockService.saveStock(form.getId(), form.getName(), form.getDryingPlace(), form.getQuantity(),
                form.getPrice(), form.getStockedDate(), form.getYield(), form.getStorageId());

        return "redirect:/stocks";
    }
}
