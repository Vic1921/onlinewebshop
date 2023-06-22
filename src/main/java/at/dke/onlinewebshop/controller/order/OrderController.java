package at.dke.onlinewebshop.controller.order;

import at.dke.onlinewebshop.controller.order.request.OrderRequest;
import at.dke.onlinewebshop.entities.Article;
import at.dke.onlinewebshop.entities.Order;
import at.dke.onlinewebshop.sql.repositories.ArticleRepository;
import at.dke.onlinewebshop.sql.repositories.CountryOrderCount;
import at.dke.onlinewebshop.sql.services.OrderFulfillmentService;
import at.dke.onlinewebshop.sql.services.OrderService;
import at.dke.onlinewebshop.sql.services.exceptions.InvalidPaymentInfoException;
import at.dke.onlinewebshop.sql.services.exceptions.MissingCustomerIdException;
import at.dke.onlinewebshop.sql.services.exceptions.MissingProductException;
import at.dke.onlinewebshop.sql.services.exceptions.StockException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderFulfillmentService orderFulfillmentService;
    private final ArticleRepository articleRepository;

    public OrderController(OrderService orderService, OrderFulfillmentService orderFulfillmentService, ArticleRepository articleRepository) {
        this.orderService = orderService;
        this.orderFulfillmentService = orderFulfillmentService;
        this.articleRepository = articleRepository;
    }

   @PostMapping("/place")
    public void placeOrder(@RequestBody OrderRequest orderRequest) throws MissingProductException, InvalidPaymentInfoException, MissingCustomerIdException {
       List<Article> articles = orderRequest.getArticleIds().stream()
               .filter(Objects::nonNull)
               .map(id -> articleRepository.findById(id).orElse(null))
               .collect(Collectors.toList());

       double price = articles.stream()
               .mapToDouble(Article::getPrice)
               .sum();

       orderService.saveOrder(orderRequest.getCustomerId(), orderRequest.getCreditCardNr(),
                orderRequest.getCreditCardName(), orderRequest.getOrderDate(), price, articles);
    }

    @PostMapping("/{orderId}/fulfill")
    public Order fulfillOrder(@PathVariable int orderId) throws StockException {
        return orderFulfillmentService.fulfillOrder(orderId);
    }

    @GetMapping("/report/{articleId}")
    public List<CountryOrderCount> report(@PathVariable int articleId) {
        return orderService.getOrdersByCustomerId(articleId);
    }

}

