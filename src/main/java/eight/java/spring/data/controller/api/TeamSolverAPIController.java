package eight.java.spring.data.controller.api;

import eight.java.spring.data.entity.TeamSolver;
import eight.java.spring.data.request.teamsolver.TeamSolverRequest;
import eight.java.spring.data.responses.GeneralResponse;
import eight.java.spring.data.service.TeamSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class TeamSolverAPIController {

    @Autowired
    TeamSolverService teamSolverService;

    //add teamsolver
    @PostMapping("/team-solver")
    public ResponseEntity getListTeamSolver(@RequestBody TeamSolverRequest teamSolverRequest){
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date birthDate = sdf.parse(teamSolverRequest.getBirthDate());
            teamSolverRequest.parseBirthDate = birthDate;
            teamSolverService.addTeamSolver(teamSolverRequest);
            generalResponse.setStatus(true);
            generalResponse.setMessage("Team successfully added");
            return ResponseEntity.ok(generalResponse);
        }catch (Exception e){
            generalResponse.setMessage(e.getMessage());
            generalResponse.setStatus(false);
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    //update teamsolver
    @PutMapping("/team-solver/{teamid}")
    public ResponseEntity updateTeamSolver(@PathVariable("teamid") Long id, @RequestBody TeamSolverRequest teamSolverRequest){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date birthDate = sdf.parse(teamSolverRequest.getBirthDate());
            teamSolverRequest.parseBirthDate = birthDate;
            GeneralResponse generalResponse = teamSolverService.updateTeamSolver(id, teamSolverRequest);
            if (generalResponse.isStatus()) {
                return ResponseEntity.ok(generalResponse);
            } else {
                return ResponseEntity.badRequest().body(generalResponse);
            }
        }catch (Exception e){
            GeneralResponse generalResponse = new GeneralResponse();
            generalResponse.setMessage(e.getMessage());
            generalResponse.setStatus(false);
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    //delete teamsolver
    @DeleteMapping("/team-solver/{teamid}")
    public ResponseEntity deleteTeamSolver(@PathVariable("teamid") Long id){
        GeneralResponse generalResponse = teamSolverService.deleteTeamSolver(id);
        if (generalResponse.isStatus()){
            return ResponseEntity.ok(generalResponse);
        }else{
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    //list teamsolver
    @GetMapping("/team-solver")
    public ResponseEntity getListTeamSolver(){
        return ResponseEntity.ok(teamSolverService.getListTeamSolvers());
    }

    //detail teamsolver
    @GetMapping("/team-solver/{teamid}")
    public ResponseEntity getDetailTeamSolver(@PathVariable("teamid") Long teamId){
        TeamSolver teamSolverDetail = teamSolverService.getDetailTeamSolver(teamId);
        if (teamSolverDetail != null){
            return ResponseEntity.ok(teamSolverDetail);
        }else{
            GeneralResponse generalResponse = new GeneralResponse();
            generalResponse.setMessage("Team Solver not found");
            generalResponse.setStatus(false);
            return ResponseEntity.status(404).body(generalResponse);
        }
    }

    //assign teamsolver
    @PutMapping("/team-solver/assign/{taskid}")
    public ResponseEntity assignTeamSolver(@PathVariable("taskid") Long id, @RequestBody List<Long>teamSolverId){
        GeneralResponse  generalResponse = teamSolverService.assignTeamSolver(id, teamSolverId);
        if (generalResponse.isStatus()){
            return ResponseEntity.ok(generalResponse);
        }else{
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

}
