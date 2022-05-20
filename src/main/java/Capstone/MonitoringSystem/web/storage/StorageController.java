package Capstone.MonitoringSystem.web.storage;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StorageController {

    private final StorageRepository sr;

    @GetMapping("/storage/{storageId}")
    public String storagePage(@PathVariable Long storageId, Model model) {

        Storage storage = sr.findById(storageId);
        List<Stock> stocks = storage.getStocks();

        if (stocks == null) {
            model.addAttribute("stocks", new ArrayList<>());
            model.addAttribute("total", 0);
            model.addAttribute("maxCapacity", storage.getMaxCapacity());
            return "wareHouse1";
        }

        Double total = storage.getTotalStockQuantity();
        for (Stock stock : stocks) {
            double rate = (stock.getQuantity() / total) * 100;
            stock.setRate((int)rate);
        }

        model.addAttribute("stocks", stocks);
        model.addAttribute("total", total);
        model.addAttribute("maxCapacity", storage.getMaxCapacity());

        return "wareHouse1";
    }
}
