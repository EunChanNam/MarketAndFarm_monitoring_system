package Capstone.MonitoringSystem.web.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @AllArgsConstructor
public class StockInputForm {

    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    private String dryingPlace;

    @NotNull
    private Double quantity;

    @NotNull
    @Min(value = 10)
    private Integer price;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockedDate;

    private Double yield;

    @NotNull
    private Long storageId;
}
