package ru.innopolis.java.attestation.service;

import jakarta.persistence.EntityNotFoundException;
import ru.innopolis.java.attestation.dto.OrderDTO;
import ru.innopolis.java.attestation.model.Order;
import ru.innopolis.java.attestation.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation.utils.DTOEntityConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(DTOEntityConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(DTOEntityConverter::convertToDTO);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = DTOEntityConverter.convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return DTOEntityConverter.convertToDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setProductId(orderDTO.getProductId());
            order.setCustomerId(orderDTO.getCustomerId());
            order.setOrderDate(orderDTO.getOrderDate());
            order.setQuantity(orderDTO.getQuantity());
            Order updatedOrder = orderRepository.save(order);
            return DTOEntityConverter.convertToDTO(updatedOrder);
        } else {
            throw new EntityNotFoundException("Order not found");
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

