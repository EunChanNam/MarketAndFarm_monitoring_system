package Capstone.MonitoringSystem.domain.Stock.stockrepository;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImp implements StockRepository{

    private final EntityManager em;

    @Override
    public Long save(Stock stock) {
        em.persist(stock);
        return stock.getId();
    }

    @Override
    public List<Stock> findBySearch(StockSearch stockSearch) {
        LocalDate recentWeek = LocalDate.now().minusDays(7);
        if (stockSearch.getName() == null && stockSearch.getDate() == null) {
            String query = "select s from Stock s join fetch s.storage " +
                    "where s.stockedDate >= :recentWeek";
            return em.createQuery(query, Stock.class)
                    .setParameter("recentWeek", recentWeek)
                    .getResultList();
        }
        else return null;
    }

    @Override
    public Stock findById(Long id) {
        String query = "select s from Stock s join fetch s.storage " +
                "where s.id = :id";
        List<Stock> result = em.createQuery(query, Stock.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) return null;
        else return result.get(0);
    }

    @Override
    public void remove(Stock stock) {
        em.remove(stock);
    }
}
