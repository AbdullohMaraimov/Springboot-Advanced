package online.pdp.spring_advanced.hw.part2.controller;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.hw.part2.model.Student;
import online.pdp.spring_advanced.hw.part2.repository.StudentRepoWithMongoTemp;
import online.pdp.spring_advanced.hw.part2.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("/save/many")
    public ResponseEntity<String> saveMany(@RequestBody List<Student> students) {
        return studentService.saveAll(students);
    }

    @GetMapping("/{studentId}")
    public Student findById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/all")
    public Page<Student> findAll(@RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "5") Integer size,
                                 @RequestParam(defaultValue = "age") String field,
                                 @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

        return studentService.getAllStudents(page, size, field, direction);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteById(@PathVariable String studentId) {
        return studentService.delete(studentId);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<String> update(@PathVariable String studentId, @RequestBody Student student) {
        return studentService.update(studentId, student);
    }

    @GetMapping("/by-group/{groupId}")
    public List<Student> findByGroupId(@PathVariable String groupId) {
        return studentService.findStudentByGroupId(groupId);
    }

    @GetMapping("/by-gender")
    public List<Student> getByGender(@RequestParam String gender) {
        return studentService.findStudentsByGender(gender);
    }



}
