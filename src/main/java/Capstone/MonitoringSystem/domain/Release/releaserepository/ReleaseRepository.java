package Capstone.MonitoringSystem.domain.Release.releaserepository;

import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Release.ReleaseSearch;

import java.util.List;

public interface ReleaseRepository {

    public Long save(Release release);

    public List<Release> findBySearch(ReleaseSearch releaseSearch);

    public Release findById(Long id);

    public void remove(Release release);
}
