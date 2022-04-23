package Capstone.MonitoringSystem.domain.Stock;

import Capstone.MonitoringSystem.domain.Storage.Storage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "stock_quantity")
    private int quantity;

    private int price;

    private LocalDate stockedDate;

    private int yield;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;
}
