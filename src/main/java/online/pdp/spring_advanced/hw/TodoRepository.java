package online.pdp.spring_advanced.hw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByLevel(Level level);
    List<Todo> findByCategory(Category category);
    List<Todo> findByDeadLine(LocalDate deadLine);
}
