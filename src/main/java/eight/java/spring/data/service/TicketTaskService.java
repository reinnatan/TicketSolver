package eight.java.spring.data.service;


import eight.java.spring.data.entity.TicketTask;
import eight.java.spring.data.request.TicketTaskRequest;
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

    public TicketTaskResponse addTicket(TicketTaskRequest ticketTaskRequest){
        TicketTask ticketTask = new TicketTask();
        ticketTask.setDescriptions(ticketTaskRequest.getDescriptions());
        ticketTask.setTitle(ticketTaskRequest.getTitle());
        ticketTask.setSolved(ticketTaskRequest.isSolved());
        TicketTask responseDB =  ticketTaskRepository.save(ticketTask);

        TicketTaskResponse responseServer = new TicketTaskResponse();
        responseServer.setId(responseDB.getId());
        responseServer.setDescriptions(responseDB.getDescriptions());
        responseServer.setTitle(responseDB.getTitle());
        responseServer.setSolved(responseDB.isSolved());

        return responseServer;
    }
}
