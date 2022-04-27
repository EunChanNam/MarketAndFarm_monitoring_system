package Capstone.MonitoringSystem.domain.Stock.stockservice;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import Capstone.MonitoringSystem.domain.Stock.StockUpdateForm;
import Capstone.MonitoringSystem.domain.Stock.stockrepository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockServiceImp implements StockService {

    private final StockRepository sr;

    @Override

    public Long saveStock(Stock stock) {
        sr.save(stock);
        return stock.getId();
    }

    @Override
    public List<Stock> findStocksBySearch(StockSearch stockSearch) {
        return sr.findBySearch(stockSearch);
    }

    @Override
    public Stock findStock(Long stockId) {
        return sr.findById(stockId);
    }

    @Override
    public void removeStock(Long stockId) {
        Stock findStock = sr.findById(stockId);
        sr.remove(findStock);
    }

    @Override
    public void updateStock(Long stockId, StockUpdateForm form) {
        //StockUpdateForm 정해지면 짜기
    }
}
