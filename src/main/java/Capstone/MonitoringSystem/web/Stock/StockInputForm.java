package Capstone.MonitoringSystem.web.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class StockInputForm {

    private Long id;

    private String name;

    private String dryingPlace;

    private Double quantity;

    private Integer price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockedDate;

    private Double yield;

    private Long storageId;
}
