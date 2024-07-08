package online.pdp.spring_advanced.service;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.StudentRequestDto;
import online.pdp.spring_advanced.dto.StudentUpdateDto;
import online.pdp.spring_advanced.entity.Student;
import online.pdp.spring_advanced.repository.StudentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student create(StudentRequestDto dto) {
        Student student = Student.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .build();
        return studentRepository.save(student);
    }

    @Override
    @Cacheable(value = "students", key = "#id")
    public Student findById(Long id) throws InterruptedException {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        TimeUnit.SECONDS.sleep(2);

        return student;
    }

    @Override
    @CacheEvict(value = "students", key = "#id")
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "students", key = "#dto.id")
    public Student update(StudentUpdateDto dto) {
        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(dto.getName());
        student.setAge(dto.getAge());

        studentRepository.save(student);
        return student;
    }
}
