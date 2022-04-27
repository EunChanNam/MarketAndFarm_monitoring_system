package Capstone.MonitoringSystem.domain.Release;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @AllArgsConstructor
public class ReleaseSearch {

    private String name;

    private Integer date;
}
