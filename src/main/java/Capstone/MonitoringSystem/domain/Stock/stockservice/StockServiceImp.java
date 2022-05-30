package Capstone.MonitoringSystem.domain.Stock.stockservice;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Search;
import Capstone.MonitoringSystem.domain.Stock.StockUpdateForm;
import Capstone.MonitoringSystem.domain.Stock.stockrepository.StockRepository;
import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StockServiceImp implements StockService {

    private final StockRepository sr;
    private final StorageRepository str;

    @Transactional
    public void saveStock(Long id, String name, String dryingPlace,Double quantity, Integer price,
                          LocalDate stockedDate, Double yield, Long storageId) {
        Storage storage = str.findByIdLazy(storageId);
        Stock stock = Stock.createStock(id, name, dryingPlace, quantity, price, stockedDate, yield, storage);
        sr.save(stock);
    }

    public List<Stock> findStocksBySearch(Search search) {
        return sr.findBySearch(search);
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
    public void updateStock(StockUpdateForm updateForm) {
        Stock stock = sr.findForUpdate(updateForm.getId());
        log.info("stockId = {}", updateForm.getId());
        Storage storage = str.findById(updateForm.getStorageId());

        stock.removeStorage();
        stock.addStorage(storage);
        stock.setName(updateForm.getName());
        stock.setPrice(updateForm.getPrice());
        stock.setDryingPlace(updateForm.getDryingPlace());
        stock.setQuantity(updateForm.getQuantity());
        stock.setStockedDate(updateForm.getStockedDate());
        stock.setYield(updateForm.getYield());
    }

    @Override
    public List<Stock> findAll() {
        return sr.findAll();
    }
}
