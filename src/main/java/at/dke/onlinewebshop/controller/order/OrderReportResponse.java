package at.dke.onlinewebshop.controller.order;

import at.dke.onlinewebshop.entities.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderReportResponse {
    private List<Order> rows;
}
