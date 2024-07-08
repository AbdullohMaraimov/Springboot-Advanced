package online.pdp.spring_advanced.service;

import online.pdp.spring_advanced.dto.StudentRequestDto;
import online.pdp.spring_advanced.dto.StudentUpdateDto;
import online.pdp.spring_advanced.entity.Student;


public interface StudentService {

    Student create(StudentRequestDto dto);

    Student findById(Long id) throws InterruptedException;

    void delete(Long id);

    Student update(StudentUpdateDto dto);
}
