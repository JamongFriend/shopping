package project.shopping.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import project.shopping.domain.Order;
import project.shopping.domain.OrderItem;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    //public List<Order> findAll(OrderSearch orderSearch) {}
}
