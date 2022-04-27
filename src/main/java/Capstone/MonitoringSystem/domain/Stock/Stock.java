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
    @Column(name = "B/L_number")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "stock_quantity")
    private double quantity;

    private int price;

    private LocalDate stockedDate;

    private double yield;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    public void addStorage(Storage storage) {
        storage.getStocks().add(this);
        setStorage(storage);
    }

    public void removeStorage() {
        getStorage().getStocks()
                .removeIf(s -> s.getId().equals(getId()));
        setStorage(null);
    }

    public void reduceStock(double quantity) {
        this.quantity -= quantity;
    }

    public static Stock createStock(Long id, String name, double quantity, int price, LocalDate stockedDate,
                                    double yield, Storage storage) {
        Stock stock = new Stock(id, name, quantity, price, stockedDate, yield);
        stock.addStorage(storage);
        return stock;
    }

    public Stock(Long id, String name, double quantity, int price, LocalDate stockedDate, double yield) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.stockedDate = stockedDate;
        this.yield = yield;
    }

    protected Stock() {
    }
}
