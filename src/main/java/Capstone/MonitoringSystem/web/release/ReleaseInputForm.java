package Capstone.MonitoringSystem.web.release;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ReleaseInputForm {

    @NotNull
    private Integer price;

    @NotNull
    private Double quantity;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releasedDate;

    @NotEmpty
    private String companyName;
}
