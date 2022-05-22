package Capstone.MonitoringSystem.web.Stock;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockUpdateForm;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.RequiredArgsConstructor;
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
public class StockNewController {

    private final StockService stockService;
    private final StorageRepository sr;

    @ModelAttribute("storages")
    public List<Storage> storages() {
        return sr.findAllStorages();
    }

    @GetMapping("/stocks/new")
    public String stockInputForm(@ModelAttribute("form") StockInputForm form) {
        return "inputListUpload";
    }

    @GetMapping("/stocks/update/{stockId}")
    public String stockUpdateForm(@PathVariable Long stockId, Model model) {

        Stock stock = stockService.findStock(stockId);
        StockUpdateForm stockUpdateForm = new StockUpdateForm(stockId, stock.getName(), stock.getDryingPlace(), stock.getQuantity(), stock.getPrice(),
                stock.getStockedDate(), stock.getYield(), stock.getStorage().getId());
        model.addAttribute("stock", stockUpdateForm);

        return "stockModifyPage";
    }
}
