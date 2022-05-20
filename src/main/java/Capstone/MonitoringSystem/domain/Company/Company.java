package Capstone.MonitoringSystem.domain.Company;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "company_name")
    private String name;

    protected Company() {
    }

    public Company(String name) {
        this.name = name;
    }
}
