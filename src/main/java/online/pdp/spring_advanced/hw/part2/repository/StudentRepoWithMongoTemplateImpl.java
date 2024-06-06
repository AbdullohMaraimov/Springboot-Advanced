package online.pdp.spring_advanced.hw.part2.repository;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.hw.part2.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class StudentRepoWithMongoTemplateImpl implements StudentRepoWithMongoTemp{

    private final MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<String> save(Student student) {
        mongoTemplate.save(student);
        return new ResponseEntity<>("Student saved", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> saveAll(List<Student> students) {
        for (Student student : students) {
            save(student);
        }
        return new ResponseEntity<>("Students saved", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(String studentId, Student student) {
        Student studentToUpdate = mongoTemplate.findById(studentId, Student.class);
        studentToUpdate.setGroup(student.getGroup());
        studentToUpdate.setName(student.getName());
        studentToUpdate.setAge(student.getAge());
        studentToUpdate.setBirthDate(student.getBirthDate());
        studentToUpdate.setGender(student.getGender());

        mongoTemplate.save(student);
        return ResponseEntity.ok("Updates successfully");
    }

    @Override
    public ResponseEntity<String> delete(String studentId) {
        mongoTemplate.remove(Objects.requireNonNull(mongoTemplate.findById(studentId, Student.class)));
        return ResponseEntity.ok("Deleted successfully");
    }

    @Override
    public Page<Student> getAllStudents(int page, int size, String fieldNameToSort, Sort.Direction direction) {
        Sort sort = Sort.by(direction, fieldNameToSort);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Query query = new Query().with(pageRequest);
        List<Student> students = mongoTemplate.find(query, Student.class);
        return PageableExecutionUtils.getPage(students, pageRequest, () -> mongoTemplate.count(new Query(), Student.class));
    }

    @Override
    public Student getStudentById(String studentId) {
        return mongoTemplate.findById(new ObjectId(studentId), Student.class);
    }

    @Override
    public List<Student> findStudentByGroupId(String groupId) {
        Criteria criteria = Criteria.where("group.id").is(groupId);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Student.class);
    }

    @Override
    public List<Student> findStudentsByGender(String gender) {
        Criteria criteria = Criteria.where("gender").is(gender);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Student.class);
    }
}
