package eight.java.spring.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TeamSolver {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Long id;
    @Column
    @Getter @Setter
    private String name;
    @Column
    @Getter @Setter
    private Date birthDate;
    @Column
    @Getter @Setter
    private String gender;
}
