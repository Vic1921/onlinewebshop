package at.dke.onlinewebshop.sql.repositories;


import at.dke.onlinewebshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o.customer AS country, COUNT(o) AS orderCount " +
            "FROM Order o " +
            "WHERE o.article.id = :articleId " +
            "GROUP BY o.customer.country " +
            "ORDER BY orderCount DESC")
    List<CountryOrderCount> findTopCountriesByArticle(@Param("articleId") int articleId, Pageable pageable);
}

