package eight.java.spring.data.respository;

import eight.java.spring.data.entity.TicketTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTaskRepository extends JpaRepository<TicketTask, Long> {
}
