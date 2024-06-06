package online.pdp.spring_advanced.hw.part2.repository;

import online.pdp.spring_advanced.hw.part2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentRepoWithMongoTemp {
    ResponseEntity<String> save(Student student);
    ResponseEntity<String> saveAll(List<Student> students);

    ResponseEntity<String> update(String studentId, Student student);

    ResponseEntity<String> delete(String studentId);

    Page<Student> getAllStudents(int page, int size, String fieldNameToSort, Sort.Direction direction);
    Student getStudentById(String studentId);

    List<Student> findStudentByGroupId(String groupId);
    List<Student> findStudentsByGender(String gender);
}
