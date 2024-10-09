package ru.innopolis.java.attestation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.innopolis.java.attestation.dto.OrderDTO;
import ru.innopolis.java.attestation.model.Order;
import ru.innopolis.java.attestation.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    public void getAllOrders() {

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

        List<OrderDTO> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals(1L, orders.get(0).getProductId());
        assertEquals(1L, orders.get(0).getCustomerId());
        assertEquals(LocalDate.now(), orders.get(0).getOrderDate());
        assertEquals(10, orders.get(0).getQuantity());
        assertEquals(2L, orders.get(1).getProductId());
        assertEquals(2L, orders.get(1).getCustomerId());
        assertEquals(LocalDate.now().minusDays(1), orders.get(1).getOrderDate());
        assertEquals(5, orders.get(1).getQuantity());
    }

    @Test
    public void getOrderById() {

        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setQuantity(10);
        Order savedOrder = orderRepository.save(order);

        Optional<OrderDTO> foundOrder = orderService.getOrderById(savedOrder.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals(1L, foundOrder.get().getProductId());
        assertEquals(1L, foundOrder.get().getCustomerId());
        assertEquals(LocalDate.now(), foundOrder.get().getOrderDate());
        assertEquals(10, foundOrder.get().getQuantity());
    }

    @Test
    public void createOrder() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductId(3L);
        orderDTO.setCustomerId(3L);
        orderDTO.setOrderDate(LocalDate.now());
        orderDTO.setQuantity(15);

        OrderDTO createdOrder = orderService.createOrder(orderDTO);

        assertNotNull(createdOrder);
        assertEquals(3L, createdOrder.getProductId());
        assertEquals(3L, createdOrder.getCustomerId());
        assertEquals(LocalDate.now(), createdOrder.getOrderDate());
        assertEquals(15, createdOrder.getQuantity());

        Optional<Order> foundOrder = orderRepository.findById(createdOrder.getId());
        assertTrue(foundOrder.isPresent());
        assertEquals(3L, foundOrder.get().getProductId());
        assertEquals(3L, foundOrder.get().getCustomerId());
        assertEquals(LocalDate.now(), foundOrder.get().getOrderDate());
        assertEquals(15, foundOrder.get().getQuantity());
    }

    @Test
    public void updateOrder() {

        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setQuantity(10);
        Order savedOrder = orderRepository.save(order);

        OrderDTO updatedOrderDTO = new OrderDTO();
        updatedOrderDTO.setProductId(2L);
        updatedOrderDTO.setCustomerId(2L);
        updatedOrderDTO.setOrderDate(LocalDate.now().minusDays(1));
        updatedOrderDTO.setQuantity(20);

        OrderDTO updatedOrder = orderService.updateOrder(savedOrder.getId(), updatedOrderDTO);

        assertNotNull(updatedOrder);
        assertEquals(2L, updatedOrder.getProductId());
        assertEquals(2L, updatedOrder.getCustomerId());
        assertEquals(LocalDate.now().minusDays(1), updatedOrder.getOrderDate());
        assertEquals(20, updatedOrder.getQuantity());

        Optional<Order> foundOrder = orderRepository.findById(updatedOrder.getId());
        assertTrue(foundOrder.isPresent());
        assertEquals(2L, foundOrder.get().getProductId());
        assertEquals(2L, foundOrder.get().getCustomerId());
        assertEquals(LocalDate.now().minusDays(1), foundOrder.get().getOrderDate());
        assertEquals(20, foundOrder.get().getQuantity());
    }

    @Test
    public void deleteOrder() {

        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setQuantity(10);
        Order savedOrder = orderRepository.save(order);

        orderService.deleteOrder(savedOrder.getId());

        Optional<Order> deletedOrder = orderRepository.findById(savedOrder.getId());
        assertTrue(deletedOrder.isEmpty());
    }
}
