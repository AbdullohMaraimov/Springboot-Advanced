package online.pdp.spring_advanced.hw.part2.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Student {

    @Id
    private String id;
    private String name;
    private Integer age;
    private Date birthDate;
    private Gender gender;
    private Group group;

}
