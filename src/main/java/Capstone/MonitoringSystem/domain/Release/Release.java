package Capstone.MonitoringSystem.domain.Release;

import Capstone.MonitoringSystem.domain.Company.Company;
import Capstone.MonitoringSystem.domain.Stock.Stock;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "releases")
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "release_id")
    private Long id;

    private int price;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private LocalDate releasedDate;
}
