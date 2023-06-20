/*
package at.dke.onlinewebshop.sql.services;
import at.dke.onlinewebshop.sql.entities.Article;
import at.dke.onlinewebshop.sql.entities.Customer;
import at.dke.onlinewebshop.sql.entities.Order;
import at.dke.onlinewebshop.sql.repositories.ArticleRepository;
import at.dke.onlinewebshop.sql.repositories.CustomerRepository;
import at.dke.onlinewebshop.sql.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ArticleRepository articleRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, ArticleRepository articleRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order placeOrder(Long customerId, Map<Long, Integer> articles) {
        // Check if the customer exists
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        // Create a new order for the customer
        Order order = new Order();
        order.setCustomer(customer);

        // For each article in the order, check if it exists and if there is enough stock
        for (Map.Entry<Long, Integer> entry : articles.entrySet()) {
            Article article = articleRepository.findById(entry.getKey()).orElseThrow(() -> new RuntimeException("Article not found"));
            int quantity = entry.getValue();

            // Check if there is enough stock
            if (article.getStock() < quantity) {
                throw new RuntimeException("Not enough stock for article " + article.getId());
            }

            // Create a new order line for the article
            OrderLine orderLine = new OrderLine();
            orderLine.setArticle(article);
            orderLine.setQuantity(quantity);
            orderLine.setOrder(order);

            // Add the order line to the order
            order.getOrderLines().add(orderLine);

            // Update the stock of the article
            article.setStock(article.getStock() - quantity);
            articleRepository.save(article);
        }

        // Save the order
        return orderRepository.save(order);
    }
}
*/
