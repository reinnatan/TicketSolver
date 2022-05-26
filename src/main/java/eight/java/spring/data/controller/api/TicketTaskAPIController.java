package eight.java.spring.data.controller.api;

import com.sun.mail.iap.Response;
import eight.java.spring.data.entity.TicketTask;
import eight.java.spring.data.request.ticket.TicketTaskRequest;
import eight.java.spring.data.request.ticket.UpdateTicketTaskStatusRequest;
import eight.java.spring.data.responses.GeneralResponse;
import eight.java.spring.data.responses.TicketTaskResponse;
import eight.java.spring.data.service.TicketTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TicketTaskAPIController {

    @Autowired
    TicketTaskService ticketTaskService;

    //get ticket task-api
    @GetMapping("/ticket-tasks")
    public List<TicketTask> getTicketTask(){
        return ticketTaskService.getAllTicketTasks();
    }

    //get ticket-for view html
    @GetMapping("/ticket-tasks-view")
    public ResponseEntity getTicketTaskView(){
        HashMap<String, List<TicketTask>> map = new HashMap<String, List<TicketTask>>();
        map.put("data",ticketTaskService.getAllTicketTasks());
        return ResponseEntity.ok(map);
    }

    //detail ticket-task
    @GetMapping("/ticket-task/{id}")
    public ResponseEntity getDetailTicketTask(@PathVariable("id") Long id){
        TicketTask detailTask = ticketTaskService.getDetailTicketTask(id);
        if (detailTask ==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(detailTask);
        }
    }

    //create ticket task
    @PostMapping("/ticket-task")
    public ResponseEntity<TicketTaskResponse> saveTicketTask(@RequestBody TicketTaskRequest ticketTaskRequest){
        TicketTaskResponse response =  ticketTaskService.addTicket(ticketTaskRequest);
        return ResponseEntity.ok(response);
    }

    //update ticket-task update status task
    @PutMapping("/ticket-task/update-status/{taskid}")
    public ResponseEntity updateStatusTicketTask(@PathVariable("taskid") Long id, @RequestBody UpdateTicketTaskStatusRequest updateTicketTaskStatusRequest){
        return ResponseEntity.ok(ticketTaskService.updateTicketStatus(id, updateTicketTaskStatusRequest));
    }

    //update ticket-task
    @PutMapping("/ticket-status/{taskid}")
    public ResponseEntity updateTicketTask(@PathVariable("taskid") Long id, @RequestBody TicketTaskRequest updateTiketTask){
        return ResponseEntity.ok(ticketTaskService.updateTicket(id, updateTiketTask));
    }

    @DeleteMapping("/ticket-task/{taskid}")
    public ResponseEntity deleteTicketTask(@PathVariable("taskid") Long id){
        GeneralResponse generalResponse = ticketTaskService.deleteTicket(id);
        if (generalResponse.isStatus()){
            return ResponseEntity.ok(generalResponse);
        }else{
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

}
