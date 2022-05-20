package Capstone.MonitoringSystem.domain.Release.releaseservice;

import Capstone.MonitoringSystem.domain.Company.Company;
import Capstone.MonitoringSystem.domain.Stock.Stock;

import java.time.LocalDate;

public interface ReleaseService {

    void saveRelease(Integer price, Double quantity, LocalDate releasedDate,
                     Stock stock, Company company);
}
