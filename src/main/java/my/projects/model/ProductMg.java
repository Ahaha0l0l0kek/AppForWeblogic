package my.projects.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Document(collection = "products")
public class ProductMg {

    @Id
    private Long id;

    @Field(value = "title")
    private String title;

    @Field(value = "price")
    private float price;
}
