package eight.java.spring.data.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class GeneralResponse {
    @Setter @Getter
    public boolean status;
    @Setter @Getter
    public String message;
    @Setter @Getter
    public Map<String, Object> data;


}
