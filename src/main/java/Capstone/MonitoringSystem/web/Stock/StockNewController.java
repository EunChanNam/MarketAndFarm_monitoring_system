package Capstone.MonitoringSystem.web.Stock;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/stocks/new")
    public String stockInput(@Validated @ModelAttribute("form") StockInputForm form,
                             BindingResult bindingResult) {

        if (idDuplicatedCheck(form.getId())) {
            bindingResult.rejectValue("id", "DuplicatedId");
            return "inputListUpload";
        }

        if (bindingResult.hasErrors()) {
            return "inputListUpload";
        }

        stockService.saveStock(form.getId(), form.getName(), form.getDryingPlace(), form.getQuantity(),
                form.getPrice(), form.getStockedDate(), form.getYield(), form.getStorageId());

        return "redirect:/stocks";
    }

    private boolean idDuplicatedCheck(Long id) {
        List<Stock> all = stockService.findAll();

        for (Stock stock : all) {
            if (stock.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
