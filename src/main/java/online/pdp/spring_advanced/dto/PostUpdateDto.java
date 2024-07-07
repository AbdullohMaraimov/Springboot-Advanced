package online.pdp.spring_advanced.dto;

import lombok.Data;

@Data
public class PostUpdateDto {
    private Integer id;
    private String title;
    private String body;
}
