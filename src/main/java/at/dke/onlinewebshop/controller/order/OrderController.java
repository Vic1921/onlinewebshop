package at.dke.onlinewebshop.controller.order;

import at.dke.onlinewebshop.sql.entities.Order;
import at.dke.onlinewebshop.sql.services.OrderFulfillmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    //    private final OrderService orderPlacementService;
    private final OrderFulfillmentService orderFulfillmentService;

    public OrderController(/*OrderPlacementService orderPlacementService,*/ OrderFulfillmentService orderFulfillmentService) {
//        this.orderPlacementService = orderPlacementService;
        this.orderFulfillmentService = orderFulfillmentService;
    }

/*    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderPlacementService.placeOrder(orderRequest.getCustomerId(), orderRequest.getArticleIds());
    }*/

    @PostMapping("/{orderId}/fulfill")
    public Order fulfillOrder(@PathVariable int orderId) {
        return orderFulfillmentService.fulfillOrder(orderId);
    }
}

