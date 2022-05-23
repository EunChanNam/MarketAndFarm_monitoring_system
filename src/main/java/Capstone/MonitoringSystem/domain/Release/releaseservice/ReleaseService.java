package Capstone.MonitoringSystem.domain.Release.releaseservice;

import Capstone.MonitoringSystem.domain.Company.Company;
import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Search;
import Capstone.MonitoringSystem.domain.Stock.Stock;

import java.time.LocalDate;
import java.util.List;

public interface ReleaseService {

    void saveRelease(Integer price, Double quantity, LocalDate releasedDate,
                     Stock stock, Company company);

    List<Release> findReleaseBySearch(Search search);

    void removeRelease(Long releaseId);
}
