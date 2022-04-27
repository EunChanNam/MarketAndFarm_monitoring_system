package Capstone.MonitoringSystem.domain.Stock.stockservice;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import Capstone.MonitoringSystem.domain.Stock.StockUpdateForm;
import Capstone.MonitoringSystem.domain.Stock.stockrepository.StockRepository;
import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockServiceImp implements StockService {

    private final StockRepository sr;
    private final StorageRepository str;

    @Transactional
    public void saveStock(Long id, String name, String dryingPlace,Double quantity, Integer price,
                          LocalDate stockedDate, Double yield, Long storageId) {
        Storage storage = str.findByIdLazy(id);
        Stock stock = Stock.createStock(id, name, dryingPlace, quantity, price, stockedDate, yield, storage);
        sr.save(stock);
    }

    public List<Stock> findStocksBySearch(StockSearch stockSearch) {
        return sr.findBySearch(stockSearch);
    }

    public Stock findStock(Long stockId) {
        return sr.findById(stockId);
    }

    @Transactional
    public void removeStock(Long stockId) {
        Stock findStock = sr.findById(stockId);
        findStock.removeStorage();
        sr.remove(findStock);
    }

    @Transactional
    public void updateStock(Long stockId, StockUpdateForm form) {
        //StockUpdateForm 정해지면 짜기
    }
}
