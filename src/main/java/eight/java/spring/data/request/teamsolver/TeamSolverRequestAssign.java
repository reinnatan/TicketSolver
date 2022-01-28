package eight.java.spring.data.request.teamsolver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeamSolverRequestAssign {
    @JsonProperty(value = "idTeamSolver")
    private List<Integer> idTeamSolver;

    public List<Integer> getIdTeamSolver() {
        return idTeamSolver;
    }

    public void setIdTeamSolver(List<Integer> idTeamSolver) {
        this.idTeamSolver = idTeamSolver;
    }
}
