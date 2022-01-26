package eight.java.spring.data.controller;

import eight.java.spring.data.entity.TicketTask;
import eight.java.spring.data.request.TicketTaskRequest;
import eight.java.spring.data.responses.TicketTaskResponse;
import eight.java.spring.data.service.TicketTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TicketTaskController {

    @Autowired
    TicketTaskService ticketTaskService;

    @GetMapping("/ticket-tasks")
    public List<TicketTask> getTicketTask(){
        return ticketTaskService.getAllTicketTasks();
    }

    @PostMapping("/ticket-task")
    public ResponseEntity<TicketTaskResponse> saveOrUpdate(@RequestBody TicketTaskRequest ticketTaskRequest){
        TicketTaskResponse response =  ticketTaskService.addTicket(ticketTaskRequest);
        return ResponseEntity.ok(response);
    }


}
