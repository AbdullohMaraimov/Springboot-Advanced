package online.pdp.spring_advanced.controller;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.StudentRequestDto;
import online.pdp.spring_advanced.dto.StudentUpdateDto;
import online.pdp.spring_advanced.entity.Student;
import online.pdp.spring_advanced.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) throws InterruptedException {
        return studentService.findById(id);
    }

    @PostMapping
    public Student postStudent(@RequestBody StudentRequestDto dto) {
        return studentService.create(dto);
    }

    @PutMapping
    public String updateStudent(@RequestBody StudentUpdateDto dto) {
        studentService.update(dto);

        return "Student updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        studentService.delete(id);
        return "Student deleted with id %d".formatted(id);
    }
}
