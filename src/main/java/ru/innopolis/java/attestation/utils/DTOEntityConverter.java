package ru.innopolis.java.attestation.utils;

import ru.innopolis.java.attestation.dto.CustomerDTO;
import ru.innopolis.java.attestation.dto.OrderDTO;
import ru.innopolis.java.attestation.dto.ProductDTO;
import ru.innopolis.java.attestation.model.Customer;
import ru.innopolis.java.attestation.model.Order;
import ru.innopolis.java.attestation.model.Product;

public class DTOEntityConverter {
    public static OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setProductId(order.getProductId());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setQuantity(order.getQuantity());
        return orderDTO;
    }

    public static Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setProductId(orderDTO.getProductId());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setQuantity(orderDTO.getQuantity());
        return order;
    }

    public static CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        return customerDTO;
    }

    public static Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        return customer;
    }

    public static ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setCost(product.getCost());
        return productDTO;
    }

    public static Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setDescription(productDTO.getDescription());
        product.setCost(productDTO.getCost());
        return product;
    }
}
