package project.shopping.repository.order.query;

import lombok.Data;
import project.shopping.api.OrderApiController;
import project.shopping.domain.Address;
import project.shopping.domain.Order;
import project.shopping.domain.OrderItem;
import project.shopping.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItem> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItems();
        List<OrderItemDto> result = orderItems.stream()
                .map(o -> new OrderItemDto(o))
                .collect(Collectors.toList());
    }
}
