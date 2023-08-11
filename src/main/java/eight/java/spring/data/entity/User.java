package eight.java.spring.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Long id;
    @Column
    @Getter @Setter
    private String phoneNumber;
    @Column
    @Getter @Setter
    private String password;
    @Column
    @Getter @Setter
    private String name;
    @Column
    @Getter @Setter
    private String address;
    @Column
    @Getter @Setter
    private String token;
    @Column
    @Getter @Setter
    private boolean isActive;
    @Column
    @Getter @Setter
    private Date dateBirth;

}
