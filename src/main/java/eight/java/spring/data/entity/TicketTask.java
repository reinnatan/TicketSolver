package eight.java.spring.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class TicketTask {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String descriptions;
    @Column
    private boolean isSolved;

    @Column(nullable = true)
    private Float latitude;

    @Column(nullable = true)
    private Float longitude;

    @ManyToMany
    private List<TeamSolver> teamSolverList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public List<TeamSolver> getTeamSolverList() {
        return teamSolverList;
    }

    public void setTeamSolverList(List<TeamSolver> teamSolverList) {
        this.teamSolverList = teamSolverList;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
