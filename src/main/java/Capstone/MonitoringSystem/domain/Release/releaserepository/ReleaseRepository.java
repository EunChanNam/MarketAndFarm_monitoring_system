package Capstone.MonitoringSystem.domain.Release.releaserepository;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Search;

import java.util.List;

public interface ReleaseRepository {

    Long save(Release release);

    List<Release> findBySearch(Search search);

    Release findById(Long id);

    void remove(Release release);
}
