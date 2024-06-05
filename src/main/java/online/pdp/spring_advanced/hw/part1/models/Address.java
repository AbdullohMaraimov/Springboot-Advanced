package online.pdp.spring_advanced.hw.part1.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

}
