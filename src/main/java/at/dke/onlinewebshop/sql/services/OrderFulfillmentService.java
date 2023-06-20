package at.dke.onlinewebshop.sql.services;

import at.dke.onlinewebshop.sql.entities.Order;
import at.dke.onlinewebshop.sql.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFulfillmentService {

    private final OrderRepository orderRepository;
    private final StockService stockService;

    public OrderFulfillmentService(OrderRepository orderRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
    }

    @Transactional
    public Order fulfillOrder(int orderId) {
        // Check if the order exists
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Check if all articles in the order are available in stock
        for (var article : order.getArticles()) {
            int quantity = article.getQuantity();

            // Check if there is enough stock of the article
            if (!stockService.hasEnoughStock(article, quantity)) {
                throw new RuntimeException("Not enough stock for article " + article.getId());
            }

            // Reduce the stock of the article
            stockService.reduceStock(article, quantity);
        }

        // Mark the order as fulfilled
        order.setFulfilled(true);
        return orderRepository.save(order);
    }
}
