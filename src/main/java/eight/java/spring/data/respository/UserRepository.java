package eight.java.spring.data.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import eight.java.spring.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);
    User findByPhoneNumberAndPassword(String phoneNumber, String password);
    User findByToken(String token);
    User findById(Integer id);
}
