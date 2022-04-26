package Capstone.MonitoringSystem.domain.Storage;

import Capstone.MonitoringSystem.domain.Stock.Stock;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private Long id;

    @Column(name = "storage_name")
    private String name;

    private int temperature;

    private int humidity;

    @OneToMany(mappedBy = "storage")
    private List<Stock> stocks;
}
