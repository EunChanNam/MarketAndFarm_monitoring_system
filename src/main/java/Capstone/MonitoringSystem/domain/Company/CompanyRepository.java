package Capstone.MonitoringSystem.domain.Company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final EntityManager em;

    @Transactional
    public void save(Company company) {
        em.persist(company);
    }

    public Company findByName(String name) {
        String query = "select c from Company c " +
                "where c.name = :name";
        List<Company> resultList = em.createQuery(query, Company.class)
                .setParameter("name", name)
                .getResultList();

        if (resultList.isEmpty()) return null;
        return resultList.get(0);
    }
}
