package online.pdp.spring_advanced.service;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.StudentRequestDto;
import online.pdp.spring_advanced.dto.StudentUpdateDto;
import online.pdp.spring_advanced.entity.Student;
import online.pdp.spring_advanced.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final ConcurrentHashMap<Long, Student> studentCache = new ConcurrentHashMap<>();

    @Override
    public Student create(StudentRequestDto dto) {
        Student student = Student.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Student findById(Long id) throws InterruptedException {

        Student cashedStudent = studentCache.get(id);
        if (cashedStudent != null) {
            return cashedStudent;
        }

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        TimeUnit.SECONDS.sleep(2);

        studentCache.put(id, student);

        return student;
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
        studentCache.remove(id);
    }

    @Override
    public void update(StudentUpdateDto dto) {

        Student cashedStudent = studentCache.get(dto.getId());
        if (cashedStudent != null) {
            cashedStudent.setName(dto.getName());
            cashedStudent.setAge(dto.getAge());
            studentRepository.save(cashedStudent);
            studentCache.put(cashedStudent.getId(), cashedStudent);
        }

        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(dto.getName());
        student.setAge(dto.getAge());

        studentRepository.save(student);
    }
}
