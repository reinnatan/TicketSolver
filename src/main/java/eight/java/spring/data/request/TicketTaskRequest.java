package eight.java.spring.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TicketTaskRequest {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "descriptions")
    private String descriptions;
    @JsonProperty(value = "isSolved")
    private boolean isSolved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}

