package Capstone.MonitoringSystem.domain.Release.releaserepository;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Release.ReleaseSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Release> findBySearch(ReleaseSearch releaseSearch) {
        LocalDate recentWeek = LocalDate.now().minusDays(7);
        if (releaseSearch.getName() == null && releaseSearch.getDate() == null) {
            String query = "select r from Release r join fetch r.company join fetch r.stock " +
                    "where r.releasedDate >= :recentWeek";
            return em.createQuery(query, Release.class)
                    .setParameter("recentWeek", recentWeek)
                    .getResultList();
        }
        else return null;
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
