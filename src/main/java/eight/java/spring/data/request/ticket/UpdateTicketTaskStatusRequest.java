package eight.java.spring.data.request.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class UpdateTicketTaskStatusRequest{
    @JsonProperty(value = "isSolved")
    @Setter @Getter
    private boolean isSolved;
}
