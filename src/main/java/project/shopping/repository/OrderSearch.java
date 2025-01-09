package project.shopping.repository;

import lombok.Getter;
import lombok.Setter;
import project.shopping.domain.OrderStatus;
import project.shopping.service.OrderService;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
