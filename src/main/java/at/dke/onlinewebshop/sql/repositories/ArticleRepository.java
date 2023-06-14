package at.dke.onlinewebshop.sql.repositories;

import at.dke.onlinewebshop.sql.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>  {

}
