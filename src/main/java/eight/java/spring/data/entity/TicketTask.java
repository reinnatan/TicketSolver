package eight.java.spring.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import eight.java.spring.data.respository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class TicketTask {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Long id;
    @Column
    @Getter @Setter
    private String title;
    @Column
    @Getter @Setter
    private String descriptions;
    @Column
    @Getter @Setter
    private boolean isSolved;
    @Column(nullable = true)
    @Getter @Setter
    private Float latitude;
    @Column(nullable = true)
    @Getter @Setter
    private Float longitude;
    @ManyToMany
    @Getter @Setter
    private List<TeamSolver> teamSolverList;
    @OneToOne
    @Getter @Setter
    private User user;

}
