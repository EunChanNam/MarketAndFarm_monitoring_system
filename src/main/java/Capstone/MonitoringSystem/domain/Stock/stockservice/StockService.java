package Capstone.MonitoringSystem.domain.Stock.stockservice;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import Capstone.MonitoringSystem.domain.Stock.StockUpdateForm;

import java.time.LocalDate;
import java.util.List;

public interface StockService {

    public Long saveStock(Long id, String name, String dryingPlace, double quantity, int price,
                          LocalDate stockedDate, double yield, Long storageId);

    public List<Stock> findStocksBySearch(StockSearch stockSearch);

    public Stock findStock(Long stockId);

    public void removeStock(Long stockId);

    public void updateStock(Long stockId, StockUpdateForm form);
}
