package Capstone.MonitoringSystem.domain.Stock.stockrepository;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.StockSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StockRepositoryImp implements StockRepository{

    private final EntityManager em;

    @Override
    public Long save(Stock stock) {
        em.persist(stock);
        return stock.getId();
    }

    @Override
    public List<Stock> findBySearch(StockSearch stockSearch) {
        if (stockSearch.getName() == null && stockSearch.getDate() == null) {
            String query = "select s from Stock s join fetch s.storage";
            return em.createQuery(query, Stock.class)
                    .getResultList();
        }
        if (stockSearch.getName().isEmpty()) {
            LocalDate minusDays = LocalDate.now().minusDays(stockSearch.getDate());
            log.info("Date = {}", minusDays);
            String query = "select s from Stock s join fetch s.storage " +
                    "where s.stockedDate >= :minusDays";
            return em.createQuery(query, Stock.class)
                    .setParameter("minusDays", minusDays)
                    .getResultList();
        }
        if (stockSearch.getDate() == null) {
            LocalDate minusDays = LocalDate.now().minusDays(7);
            log.info("Date = {}", minusDays);
            String target = stockSearch.getName();
            String query = "select s from Stock s join fetch s.storage " +
                    "where s.stockedDate >= :minusDays " +
                    "and s.name = :target";
            return em.createQuery(query, Stock.class)
                    .setParameter("minusDays", minusDays)
                    .setParameter("target", target)
                    .getResultList();
        }
        else {
            LocalDate minusDays = LocalDate.now().minusDays(stockSearch.getDate());
            log.info("Date = {}", minusDays);
            String target = stockSearch.getName();
            String query = "select s from Stock s join fetch s.storage " +
                    "where s.stockedDate >= :minusDays " +
                    "and s.name = :target";
            return em.createQuery(query, Stock.class)
                    .setParameter("minusDays", minusDays)
                    .setParameter("target", target)
                    .getResultList();
        }
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

    @Override
    public List<Stock> findAll() {
        String query = "select s from Stock s";
        return em.createQuery(query, Stock.class)
                .getResultList();
    }
}
