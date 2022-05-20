package Capstone.MonitoringSystem.domain.Release.releaseservice;

import Capstone.MonitoringSystem.domain.Company.Company;

import java.time.LocalDate;

public interface ReleaseService {

    void saveRelease(Integer price, Double quantity, LocalDate releasedDate,
                     Long stockId, Company company);
}
