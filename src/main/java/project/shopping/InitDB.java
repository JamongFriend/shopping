package project.shopping;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.shopping.domain.*;
import project.shopping.domain.item.Book;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("서울", "1", "1111"));
            em.persist(member);

            Book book1 = new Book();
            book1.setName("book1");
            book1.setPrice(10000);
            book1.setStockQuantity(50);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("book2");
            book2.setPrice(12000);
            book2.setStockQuantity(50);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 12000, 1);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = new Member();
            member.setName("userB");
            member.setAddress(new Address("서울", "1", "1111"));
            em.persist(member);

            Book book3 = new Book();
            book3.setName("book3");
            book3.setPrice(10000);
            book3.setStockQuantity(50);
            em.persist(book3);

            Book book4 = new Book();
            book4.setName("book4");
            book4.setPrice(12000);
            book4.setStockQuantity(50);
            em.persist(book4);

            OrderItem orderItem1 = OrderItem.createOrderItem(book3, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book4, 12000, 1);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
