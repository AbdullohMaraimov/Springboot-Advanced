package online.pdp.spring_advanced.product;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Date date;
}
