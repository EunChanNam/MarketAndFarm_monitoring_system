package Capstone.MonitoringSystem.domain.Release.releaserepository;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReleaseRepositoryImp implements ReleaseRepository{

    private final EntityManager em;

    @Override
    public Long save(Release release) {
        em.persist(release);
        return release.getId();
    }

    @Override
    public List<Release> findBySearch(Search search) {
        if ((search.getName() == null && search.getDate() == null) ||
                search.getName().isEmpty() && search.getDate() == null) {
            String query = "select r from Release r join fetch r.stock " +
                    "join fetch r.company";
            return em.createQuery(query, Release.class)
                    .getResultList();
        }
        if (search.getName().isEmpty()) {
            LocalDate minusDays = LocalDate.now().minusDays(search.getDate());
            String query = "select r from Release r join fetch r.stock " +
                    "join fetch r.company " +
                    "where r.releasedDate >= :minusDays";
            return em.createQuery(query, Release.class)
                    .setParameter("minusDays", minusDays)
                    .getResultList();
        }
        if (search.getDate() == null) {
            LocalDate minusDays = LocalDate.now().minusDays(7);
            String target = search.getName();
            String query = "select r from Release r join fetch r.stock s " +
                    "join fetch r.company " +
                    "where r.releasedDate >= :minusDays " +
                    "and s.name = :target";
            return em.createQuery(query, Release.class)
                    .setParameter("minusDays", minusDays)
                    .setParameter("target", target)
                    .getResultList();
        }
        else {
            LocalDate minusDays = LocalDate.now().minusDays(search.getDate());
            String target = search.getName();
            String query = "select r from Release r join fetch r.stock s " +
                    "join fetch r.company " +
                    "where r.releasedDate >= :minusDays " +
                    "and s.name = :target";
            return em.createQuery(query, Release.class)
                    .setParameter("minusDays", minusDays)
                    .setParameter("target", target)
                    .getResultList();
        }
    }

    @Override
    public Release findById(Long id) {
        String query = "select r from Release r join fetch r.company join fetch r.stock " +
                "where r.id = :id";
        List<Release> result = em.createQuery(query, Release.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) return null;
        else return result.get(0);
    }

    @Override
    public void remove(Release release) {
        em.remove(release);
    }
}
