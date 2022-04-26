package Capstone.MonitoringSystem.domain.Storage.storagerepository;

import Capstone.MonitoringSystem.domain.Storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class StorageRepository {

    private final EntityManager em;

    public Long save(Storage storage) {
        em.persist(storage);
        return storage.getId();
    }
}
