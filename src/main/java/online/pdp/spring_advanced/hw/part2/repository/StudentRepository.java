package online.pdp.spring_advanced.hw.part2.repository;

import online.pdp.spring_advanced.hw.part2.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findStudentsByGroup_Id(String groupId);

    @Query("{gender : {$eq : '?0'}}")
    List<Student> getStudentsByG(String gender);

}
