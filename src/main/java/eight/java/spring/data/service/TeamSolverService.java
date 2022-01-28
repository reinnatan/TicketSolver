package eight.java.spring.data.service;

import eight.java.spring.data.entity.TeamSolver;
import eight.java.spring.data.entity.TicketTask;
import eight.java.spring.data.request.teamsolver.TeamSolverRequest;
import eight.java.spring.data.responses.GeneralResponse;
import eight.java.spring.data.respository.TeamSolverRepository;
import eight.java.spring.data.respository.TicketTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TeamSolverService {

    @Autowired
    private TeamSolverRepository teamSolverRepository;

    @Autowired
    private TicketTaskRepository ticketTaskRepository;

    @PersistenceContext
    private EntityManager em;

    //add teamsolver
    public TeamSolver addTeamSolver(TeamSolverRequest teamSolverRequest) {
        TeamSolver teamSolver = new TeamSolver();
        teamSolver.setName(teamSolverRequest.getName());
        teamSolver.setGender(teamSolverRequest.getGender());
        teamSolver.setBirthDate(teamSolverRequest.parseBirthDate);
        teamSolverRepository.save(teamSolver);

        return teamSolver;
    }

    //update teamsolver
    public GeneralResponse updateTeamSolver(Long id, TeamSolverRequest teamSolverRequest){
        Optional<TeamSolver> optionalTeamSolver = teamSolverRepository.findById(id);
        GeneralResponse generalResponse = new GeneralResponse();
        if (optionalTeamSolver.isPresent()){
            TeamSolver findTeamSolver = optionalTeamSolver.get();
            findTeamSolver.setBirthDate(teamSolverRequest.parseBirthDate);
            findTeamSolver.setGender(teamSolverRequest.getGender());
            findTeamSolver.setName(teamSolverRequest.getName());
            teamSolverRepository.save(findTeamSolver);

            generalResponse.setMessage("Update team solver is succeed");
            generalResponse.setStatus(true);
            return generalResponse;

        }else{
            generalResponse.setMessage("Failed update team solver");
            generalResponse.setStatus(false);
            return generalResponse;
        }

    }

    //delete teamsolver
    public GeneralResponse deleteTeamSolver(Long id){
        Optional<TeamSolver> optionalTeamSolver = teamSolverRepository.findById(id);
        GeneralResponse generalResponse =  new GeneralResponse();
        if (optionalTeamSolver.isPresent()){
            teamSolverRepository.delete(optionalTeamSolver.get());
            generalResponse.setStatus(true);
            generalResponse.setMessage("Team solver deleted");
            return generalResponse;
        }else{
            generalResponse.setMessage("Team solver failed deleted");
            generalResponse.setStatus(false);
            return generalResponse;
        }
    }
    //list teamsolver
    public List<TeamSolver> getListTeamSolvers(){
        return teamSolverRepository.findAll();
    }

    //detail teamsolver
    public TeamSolver getDetailTeamSolver(Long id){
        Optional<TeamSolver> optionalTeamSolver = teamSolverRepository.findById(id);
        if (optionalTeamSolver.isPresent()){
            return optionalTeamSolver.get();
        }else{
            return null;
        }
    }
    
    //assign teamsolver
    public GeneralResponse assignTeamSolver(Long idTicket, List<Long> teamSolverList){
        Optional<TicketTask> findTicket = ticketTaskRepository.findById(idTicket);
        GeneralResponse generalResponse = new GeneralResponse();
        if (findTicket.isPresent()){
            TicketTask ticketTask = findTicket.get();
            List<CharSequence> listTeamSolverId = new ArrayList<CharSequence>();
            for(Long idTemp: teamSolverList){
                listTeamSolverId.add(idTemp+"");
            }
            String joinedId = String.join(",", listTeamSolverId);
            List<TeamSolver> listTeamSolver= (List<TeamSolver>) em.createQuery(
                    " FROM "+TeamSolver.class.getName()+" WHERE id IN ("+joinedId+")").getResultList();

            ticketTask.setTeamSolverList(listTeamSolver);
            ticketTaskRepository.save(ticketTask);

            generalResponse.setStatus(true);
            generalResponse.setMessage("Team has assign to ticket");
            return generalResponse;
        }else{
            generalResponse.setStatus(false);
            generalResponse.setMessage("Ticket Task not found");
            return generalResponse;
        }


    }
}
