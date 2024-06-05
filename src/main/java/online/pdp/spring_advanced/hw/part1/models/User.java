package online.pdp.spring_advanced.hw.part1.models;

import lombok.*;
import org.bson.Document;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    private ObjectId mongoId;
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(Document document) {
        this.mongoId = document.getObjectId("_id");
        this.id = document.getInteger("id");
        this.name = document.getString("name");
        this.username = document.getString("username");
        this.email = document.getString("email");

        Document addressDoc = document.get("address", Document.class);
        Document geoDoc = addressDoc.get("geo", Document.class);
        Geo geo = new Geo(geoDoc.getString("lat"), geoDoc.getString("lng"));
        Address address = Address.builder()
                .street(addressDoc.getString("street"))
                .city(addressDoc.getString("city"))
                .zipcode(addressDoc.getString("zipcode"))
                .suite(addressDoc.getString("suite"))
                .geo(geo)
                .build();

        this.address = address;
        this.phone = document.getString("phone");
        this.website = document.getString("website");

        Document companyDoc = document.get("company", Document.class);
        Company company = Company.builder()
                .name(companyDoc.getString("name"))
                .bs(companyDoc.getString("bs"))
                .catchPhrase(companyDoc.getString("catchPhrase"))
                .build();

        this.company = company;
    }

}










