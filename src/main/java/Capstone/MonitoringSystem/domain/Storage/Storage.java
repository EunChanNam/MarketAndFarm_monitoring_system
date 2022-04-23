package Capstone.MonitoringSystem.domain.Storage;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
