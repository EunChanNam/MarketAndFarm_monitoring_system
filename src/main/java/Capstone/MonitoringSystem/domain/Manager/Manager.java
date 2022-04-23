package Capstone.MonitoringSystem.domain.Manager;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Manager {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    private String name;

    private String loginId;

    private String pw;
}
