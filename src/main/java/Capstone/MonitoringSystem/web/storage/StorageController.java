package Capstone.MonitoringSystem.web.storage;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.stockrepository.StockRepository;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StorageController {

    private final StorageRepository sr;
    private final StockRepository stockRepository;

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

        List<StockDto> stockDtos = getStockDtos(stocks, total, storageId);

        log.info("size = {}", stockDtos.size());

        model.addAttribute("stocks", stocks);
        model.addAttribute("stockDtos", stockDtos);
        model.addAttribute("total", total);
        model.addAttribute("maxCapacity", storage.getMaxCapacity());

        return "wareHouse1";
    }

    private List<StockDto> getStockDtos(List<Stock> stocks, Double total, Long storageId) {
        List<String> stockNames = stockRepository.findStockNamesByStorage(storageId);
        List<StockDto> storageDtos = stockNames.stream().map(StockDto::new)
                .collect(Collectors.toList());

        for (StockDto storageDto : storageDtos) {

            Double totalQuantity = 0.0;
            for (Stock stock : stocks) {
                if (stock.getName().equals(storageDto.getName())) {
                    totalQuantity += stock.getQuantity();
                }
            }
            int rate = (int)((totalQuantity / total) * 100);
            storageDto.setRate(rate);
        }

        return storageDtos;
    }

    @Data
    static class StockDto {
        private String name;
        private int rate;

        public StockDto(String name) {
            this.name = name;
        }
    }

}
