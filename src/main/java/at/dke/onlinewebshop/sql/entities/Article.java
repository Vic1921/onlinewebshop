package at.dke.onlinewebshop.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "article_id", nullable = false)
    private int id;

    @Column(name = "article_name", nullable = false)
    private String name;

    @Column(name = "article_price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_email")
    private Customer customer;
}