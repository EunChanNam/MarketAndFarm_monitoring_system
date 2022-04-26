package Capstone.MonitoringSystem.domain.Manager.managerrepository;

import Capstone.MonitoringSystem.domain.Manager.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManagerRepository {

    private final EntityManager em;

    public void save(Manager manager) {
        em.persist(manager);
    }

    public List<Manager> findByLoginId(String loginId) {
        String query = "select m from Manager m where m.loginId = :loginId";
        return em.createQuery(query, Manager.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }
}
