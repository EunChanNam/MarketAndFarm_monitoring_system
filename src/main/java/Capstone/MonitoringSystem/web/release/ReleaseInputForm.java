package Capstone.MonitoringSystem.web.release;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReleaseInputForm {

    private Integer price;

    private Double quantity;

    private LocalDate releasedDate;

    private String companyName;
}
