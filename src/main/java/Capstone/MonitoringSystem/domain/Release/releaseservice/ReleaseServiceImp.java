package Capstone.MonitoringSystem.domain.Release.releaseservice;

import Capstone.MonitoringSystem.domain.Company.Company;
import Capstone.MonitoringSystem.domain.Release.Release;
import Capstone.MonitoringSystem.domain.Release.releaserepository.ReleaseRepository;
import Capstone.MonitoringSystem.domain.Search;
import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReleaseServiceImp implements ReleaseService{

    private final ReleaseRepository releaseRepository;

    @Transactional
    public void saveRelease(Integer price, Double quantity, LocalDate releasedDate, Stock stock, Company company) {

        Release release = Release.createRelease(price, quantity, releasedDate, stock, company);
        releaseRepository.save(release);
    }

    public List<Release> findReleaseBySearch(Search search) {
        return releaseRepository.findBySearch(search);
    }

    @Transactional
    public void removeRelease(Long releaseId) {
        Release release = releaseRepository.findById(releaseId);

        releaseRepository.remove(release);
    }
}
