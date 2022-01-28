package eight.java.spring.data.request.teamsolver;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.util.Date;

public class TeamSolverRequest {

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "birtDate")
    private String birthDate;
    @JsonProperty(value = "gender")
    private String gender;

    public Date parseBirthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
