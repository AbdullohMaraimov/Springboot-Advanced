package online.pdp.spring_advanced.hw.part2.service;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.hw.part2.model.Student;
import online.pdp.spring_advanced.hw.part2.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<String> save(Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> saveAll(List<Student> students) {
        studentRepository.saveAll(students);
        return new ResponseEntity<>("Students of amount %d created successfully".formatted(students.size()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(String studentId, Student student) {
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student with id %s not found".formatted(studentId)));

        studentToUpdate.setId(student.getId());
        studentToUpdate.setName(student.getName());
        studentToUpdate.setAge(student.getAge());
        studentToUpdate.setBirthDate(student.getBirthDate());
        studentToUpdate.setGender(student.getGender());
        studentToUpdate.setGroup(student.getGroup());

        studentRepository.save(studentToUpdate);
        return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(String studentId) {
        studentRepository.deleteById(studentId);
        return new ResponseEntity<>("Student with id %s deleted".formatted(studentId), HttpStatus.OK);
    }

    @Override
    public Page<Student> getAllStudents(int page, int size, String fieldNameToSort, Sort.Direction direction) {
        Sort sort = Sort.by(direction, fieldNameToSort);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return studentRepository.findAll(pageRequest);
    }

    @Override
    public Student getStudentById(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student with id %s"));
        return student;
    }

    @Override
    public List<Student> findStudentByGroupId(String groupId) {
        return studentRepository.findStudentsByGroup_Id(groupId);
    }

    @Override
    public List<Student> findStudentsByGender(String gender) {
        return studentRepository.getStudentsByG(gender);
    }
}
