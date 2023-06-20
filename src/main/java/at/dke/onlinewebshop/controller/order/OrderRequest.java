package at.dke.onlinewebshop.controller.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private int customerId;
    private List<Integer> articleIds;
    // getters and setters
}