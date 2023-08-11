package eight.java.spring.data.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

public class UserRequest {
    @JsonProperty(value = "phoneNumber")
    @Setter @Getter
    private String phoneNumber;
    @JsonProperty(value = "password")
    @Setter @Getter
    private String password;
    @JsonProperty(value = "newPassword")
    @Setter @Getter
    private String newPassword;
    @JsonProperty(value = "name")
    @Setter @Getter
    private String name;
    @JsonProperty(value = "address")
    @Setter @Getter
    private String address;
    @Setter @Getter
    private String token;
    @Setter @Getter
    private boolean isActive;
    @JsonProperty(value = "dateBirth")
    @Setter @Getter
    private String dateBirth;
}
