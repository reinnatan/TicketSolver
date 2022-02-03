package eight.java.spring.data.controller.view;

import eight.java.spring.data.entity.TicketTask;
import eight.java.spring.data.service.TicketTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TikcetTaskViewController {

    @Autowired
    TicketTaskService ticketTaskService;

    @GetMapping("/")
    public String index(Model model){
        List<TicketTask> tickets = ticketTaskService.getAllTicketTasks();
        model.addAttribute("ticketTasks",tickets);
        return "index";
    }

    @GetMapping("/ticket-task-point")
    public List<TicketTask> getTicketTask(){
        return ticketTaskService.getAllTicketTasks();
    }


}
