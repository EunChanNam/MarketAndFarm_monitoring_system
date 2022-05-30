package Capstone.MonitoringSystem.domain.Manager.managerrepository;

import Capstone.MonitoringSystem.domain.Manager.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ManagerRepository {

    private final EntityManager em;

    public void save(Manager manager) {
        em.persist(manager);
    }

    public Optional<Manager> findByLoginId(String loginId) {
        String query = "select m from Manager m where m.loginId = :loginId";
        List<Manager> managers = em.createQuery(query, Manager.class)
                .setParameter("loginId", loginId)
                .getResultList();

        if (managers.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(managers.get(0));
    }
}
