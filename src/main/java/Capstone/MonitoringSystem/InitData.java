package Capstone.MonitoringSystem;

import Capstone.MonitoringSystem.domain.Storage.Storage;
import Capstone.MonitoringSystem.domain.Storage.storagerepository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final Init init;

    @PostConstruct
    public void initialize() {
        init.initDB();
    }

    @Component
    @RequiredArgsConstructor
    static class Init {

        private final StorageRepository sr;

        @Transactional
        public void initDB() {
            Storage storage1 = new Storage();
            storage1.setName("Storage1");
            Storage storage2 = new Storage();
            storage2.setName("Storage2");
            Storage storage3 = new Storage();
            storage3.setName("Storage3");
            sr.save(storage1);
            sr.save(storage2);
            sr.save(storage3);

        }
    }

}
