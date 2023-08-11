package eight.java.spring.data.request.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TicketTaskRequest {

    @JsonProperty(value = "title")
    @Setter @Getter
    public String title;
    @JsonProperty(value = "description")
    @Setter @Getter
    public String description;
    @JsonProperty(value = "isSolved")
    @Setter @Getter
    public boolean isSolved;
    @JsonProperty(value = "latitude")
    @Setter @Getter
    public float latitude;
    @JsonProperty(value = "longitude")
    @Setter @Getter
    public float longitude;
}

