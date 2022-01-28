package eight.java.spring.data.request.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateTicketTaskStatusRequest{
    @JsonProperty(value = "isSolved")
    private boolean isSolved;

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
