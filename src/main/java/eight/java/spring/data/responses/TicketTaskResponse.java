package eight.java.spring.data.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


public class TicketTaskResponse {
    @Setter @Getter
    public Long id;
    @Setter @Getter
    public String title;
    @Setter @Getter
    public String descriptions;
    @Setter @Getter
    public boolean isSolved;
    @Setter @Getter
    public float latitude;
    @Setter @Getter
    public float longitude;
}
