/*
package at.dke.onlinewebshop.sql.services;
import at.dke.onlinewebshop.sql.entities.Article;
import at.dke.onlinewebshop.sql.entities.Order;
import at.dke.onlinewebshop.sql.entities.Warehouse;
import at.dke.onlinewebshop.sql.repositories.OrderRepository;
import at.dke.onlinewebshop.sql.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFulfillmentService {

    private final OrderRepository orderRepository;
    private final WarehouseRepository warehouseRepository;

    public OrderFulfillmentService(OrderRepository orderRepository, WarehouseRepository warehouseRepository) {
        this.orderRepository = orderRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public Order fulfillOrder(Long orderId, Long warehouseId) {
        // Check if the order exists
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Check if the warehouse exists
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new RuntimeException("Warehouse not found"));

        // Check if all articles in the order are available in the warehouse
        for (OrderLine orderLine : order.getOrderLines()) {
            Article article = orderLine.getArticle();
            int quantity = orderLine.getQuantity();

            // Check if the warehouse has enough stock of the article
            if (!warehouse.hasEnoughStock(article, quantity)) {
                throw new RuntimeException("Not enough stock in warehouse for article " + article.getId());
            }

            // Update the stock in the warehouse
            warehouse.reduceStock(article, quantity);
            warehouseRepository.save(warehouse);
        }

        // Mark the order as fulfilled
        order.setFulfilled(true);
        return orderRepository.save(order);
    }
}
*/
