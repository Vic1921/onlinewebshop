package at.dke.onlinewebshop.nosql.entities;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "articles")
public class ArticleNoSQL {
    @Id
    private String id;
    private String articleName;
    private double articlePrice;
    private int articleQuantity;
}
