package eight.java.spring.data.service;


import eight.java.spring.data.entity.TicketTask;
import eight.java.spring.data.request.ticket.TicketTaskRequest;
import eight.java.spring.data.request.ticket.UpdateTicketTaskStatusRequest;
import eight.java.spring.data.responses.GeneralResponse;
import eight.java.spring.data.responses.TicketTaskResponse;
import eight.java.spring.data.respository.TicketTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTaskService {

    @Autowired
    private TicketTaskRepository ticketTaskRepository;

    public List<TicketTask> getAllTicketTasks(){
        return ticketTaskRepository.findAll();
    }

    public TicketTask getDetailTicketTask(Long id){
        if (ticketTaskRepository.findById(id).isPresent()){
            return ticketTaskRepository.findById(id).get();
        }
        return null;
    }

    public TicketTaskResponse addTicket(TicketTaskRequest ticketTaskRequest){
        TicketTask ticketTask = new TicketTask();
        ticketTask.setDescriptions(ticketTaskRequest.getDescription());
        ticketTask.setTitle(ticketTaskRequest.getTitle());
        ticketTask.setSolved(ticketTaskRequest.isSolved());
        ticketTask.setLatitude(ticketTaskRequest.getLatitude());
        ticketTask.setLongitude(ticketTaskRequest.getLongitude());
        TicketTask responseDB =  ticketTaskRepository.save(ticketTask);

        TicketTaskResponse responseServer = new TicketTaskResponse();
        responseServer.setId(responseDB.getId());
        responseServer.setDescriptions(responseDB.getDescriptions());
        responseServer.setTitle(responseDB.getTitle());
        responseServer.setSolved(responseDB.isSolved());
        responseServer.setLatitude(responseDB.getLatitude());
        responseDB.setLongitude(responseDB.getLongitude());

        return responseServer;
    }

    public TicketTask updateTicketStatus(Long taskid, UpdateTicketTaskStatusRequest updateTicketTaskStatusRequest){
        TicketTask ticketTask = ticketTaskRepository.findById(taskid).get();
        ticketTask.setSolved(updateTicketTaskStatusRequest.isSolved());
        ticketTaskRepository.save(ticketTask);
        return ticketTask;
    }

    public TicketTask updateTicket(Long taskid, TicketTaskRequest updateTicketRequest){
        GeneralResponse generalResponse = new GeneralResponse();

        TicketTask updateTicketTask = ticketTaskRepository.findById(taskid).get();
        updateTicketTask.setDescriptions(updateTicketRequest.getDescription());
        updateTicketTask.setTitle(updateTicketRequest.getTitle());
        updateTicketTask.setSolved(updateTicketRequest.isSolved());
        updateTicketTask.setLongitude(updateTicketRequest.getLongitude());
        updateTicketTask.setLatitude(updateTicketRequest.getLatitude());
        ticketTaskRepository.save(updateTicketTask);
        return updateTicketTask;
    }

    public GeneralResponse deleteTicket(Long id){
        GeneralResponse generalResponse = new GeneralResponse();
        if (ticketTaskRepository.findById(id).isPresent()){
            TicketTask findTicket =  ticketTaskRepository.findById(id).get();
            ticketTaskRepository.delete(findTicket);
            generalResponse.setStatus(true);
            generalResponse.setMessage("Successfully for delete the ticket");
        } else{
            generalResponse.setStatus(false);
            generalResponse.setMessage("Ticket not found for id :"+id);
        }
        return generalResponse;
    }
}
