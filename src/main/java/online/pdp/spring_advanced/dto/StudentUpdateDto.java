package online.pdp.spring_advanced.dto;

import lombok.Data;

@Data
public class StudentUpdateDto {
    private Long id;
    private String name;
    private int age;
}
