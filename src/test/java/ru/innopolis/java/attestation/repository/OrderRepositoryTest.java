package ru.innopolis.java.attestation.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.innopolis.java.attestation.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    public void saveOrder() {

        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setQuantity(10);

        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder);
        assertEquals(1L, savedOrder.getProductId());
        assertEquals(1L, savedOrder.getCustomerId());
        assertEquals(LocalDate.now(), savedOrder.getOrderDate());
        assertEquals(10, savedOrder.getQuantity());
    }

    @Test
    public void findAllOrders() {

        Order order1 = new Order();
        order1.setProductId(1L);
        order1.setCustomerId(1L);
        order1.setOrderDate(LocalDate.now());
        order1.setQuantity(10);
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setProductId(2L);
        order2.setCustomerId(2L);
        order2.setOrderDate(LocalDate.now().minusDays(1));
        order2.setQuantity(5);
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findAll();

        assertNotNull(orders);
        assertEquals(2, orders.size());
    }

    @Test
    public void findOrderById() {

        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setQuantity(10);
        Order savedOrder = orderRepository.save(order);

        Optional<Order> foundOrder = orderRepository.findById(savedOrder.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals(1L, foundOrder.get().getProductId());
        assertEquals(1L, foundOrder.get().getCustomerId());
        assertEquals(LocalDate.now(), foundOrder.get().getOrderDate());
        assertEquals(10, foundOrder.get().getQuantity());
    }

    @Test
    public void updateOrder() {

        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setQuantity(10);
        Order savedOrder = orderRepository.save(order);

        savedOrder.setProductId(2L);
        savedOrder.setCustomerId(2L);
        savedOrder.setOrderDate(LocalDate.now().minusDays(1));
        savedOrder.setQuantity(20);

        Order updatedOrder = orderRepository.save(savedOrder);

        assertNotNull(updatedOrder);
        assertEquals(2L, updatedOrder.getProductId());
        assertEquals(2L, updatedOrder.getCustomerId());
        assertEquals(LocalDate.now().minusDays(1), updatedOrder.getOrderDate());
        assertEquals(20, updatedOrder.getQuantity());
    }

    @Test
    public void deleteOrder() {

        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setQuantity(10);
        Order savedOrder = orderRepository.save(order);

        orderRepository.delete(savedOrder);
        Optional<Order> deletedOrder = orderRepository.findById(savedOrder.getId());

        assertTrue(deletedOrder.isEmpty());
    }
}
