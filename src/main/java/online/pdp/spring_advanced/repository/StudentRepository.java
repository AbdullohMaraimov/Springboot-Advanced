package online.pdp.spring_advanced.repository;
import online.pdp.spring_advanced.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
