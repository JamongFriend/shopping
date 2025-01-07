package project.shopping.service;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.shopping.domain.Delivery;
import project.shopping.domain.Member;
import project.shopping.domain.Order;
import project.shopping.domain.OrderItem;
import project.shopping.domain.item.Item;
import project.shopping.repository.ItemRepository;
import project.shopping.repository.MemberRepository;
import project.shopping.repository.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, item, orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    // public List<Order> findOrders(OrderSearch orderSearch){
    //     retrun orderRepository.findAll(orderSearch);
    // }

}
