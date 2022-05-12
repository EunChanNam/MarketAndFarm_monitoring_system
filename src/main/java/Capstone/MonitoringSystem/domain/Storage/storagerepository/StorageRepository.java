package Capstone.MonitoringSystem.domain.Storage.storagerepository;

import Capstone.MonitoringSystem.domain.Storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StorageRepository {

    private final EntityManager em;

    public Long save(Storage storage) {
        em.persist(storage);
        return storage.getId();
    }

    public Storage findByIdLazy(Long id) {
        return em.find(Storage.class, id);
    }

    public Storage findById(Long id) {
        String query = "select distinct s from Storage s " +
                "join fetch s.stocks " +
                "where s.id =:id";
        List<Storage> storages = em.createQuery(query, Storage.class)
                .setParameter("id", id)
                .getResultList();

        if (storages.isEmpty()) return null;
        else return storages.get(0);
    }

    public List<Storage> findAllStorages() {
        String query = "select s from Storage s";
        return em.createQuery(query, Storage.class)
                .getResultList();
    }
}
