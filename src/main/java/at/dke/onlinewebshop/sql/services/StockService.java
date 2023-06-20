package at.dke.onlinewebshop.sql.services;

import at.dke.onlinewebshop.sql.entities.Article;
import at.dke.onlinewebshop.sql.repositories.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final ArticleRepository articleRepository;

    public StockService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void reduceStock(Article article, int quantity) {
        if (hasEnoughStock(article, quantity)) {
            article.setQuantity(article.getQuantity() - quantity);
            articleRepository.save(article);
        } else {
            throw new IllegalArgumentException("Not enough stock for article " + article.getId());
        }
    }

    public boolean hasEnoughStock(Article article, int quantity) {
        return article.getQuantity() >= quantity;
    }
}

