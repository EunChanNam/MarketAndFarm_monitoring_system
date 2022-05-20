package Capstone.MonitoringSystem.domain.Stock.stockrepository;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Release.ReleaseSearch;
import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;

import java.util.List;

public interface StockRepository {

    public Long save(Stock stock);

    public List<Stock> findBySearch(StockSearch stockSearch);

    public Stock findById(Long id);

    public void remove(Stock stock);

    List<Stock> findAll();
}
