package Capstone.MonitoringSystem.domain.Stock.stockservice;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import Capstone.MonitoringSystem.domain.Stock.StockUpdateForm;

import java.time.LocalDate;
import java.util.List;

public interface StockService {

    public void saveStock(Long id, String name, String dryingPlace, Double quantity, Integer price,
                          LocalDate stockedDate, Double yield, Long storageId);

    public List<Stock> findStocksBySearch(StockSearch stockSearch);

    public Stock findStock(Long stockId);

    public void removeStock(Long stockId);

    List<Stock> findAll();

    void updateStock(StockUpdateForm updateForm);
}
