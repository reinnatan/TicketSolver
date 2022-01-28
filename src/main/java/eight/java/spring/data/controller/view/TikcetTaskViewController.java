package eight.java.spring.data.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TikcetTaskViewController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
