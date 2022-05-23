package Capstone.MonitoringSystem.domain.Stock.stockrepository;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Search;

import java.util.List;

public interface StockRepository {

    public Long save(Stock stock);

    public List<Stock> findBySearch(Search search);

    public Stock findById(Long id);

    Stock findForUpdate(Long id);

    public void remove(Stock stock);

    List<Stock> findAll();

    List<String> findStockNamesByStorage(Long storageId);
}
