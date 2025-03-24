package project.shopping.repository.order.query;

import lombok.Data;
import project.shopping.domain.OrderItem;

@Data
public class OrderItemDto {
    private String name;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        name = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
