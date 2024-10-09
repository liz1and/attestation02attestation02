package ru.innopolis.java.attestation.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import ru.innopolis.java.attestation.dto.CustomerDTO;
import ru.innopolis.java.attestation.dto.OrderDTO;
import ru.innopolis.java.attestation.dto.ProductDTO;
import ru.innopolis.java.attestation.model.Customer;
import ru.innopolis.java.attestation.model.Order;
import ru.innopolis.java.attestation.model.Product;

import java.time.LocalDate;

public class DTOEntityConverterTest {

    @Test
    public void testConvertOrderDTOToEntity() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);

        Order order = DTOEntityConverter.convertToEntity(orderDTO);

        assertEquals(1L, order.getId());
    }

    @Test
    public void testConvertProductDTOToEntity() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("Молоко");

        Product product = DTOEntityConverter.convertToEntity(productDTO);

        assertEquals("Молоко", product.getDescription());
    }

    @Test
    public void testConvertCustomerDTOToEntity() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Иван");
        customerDTO.setSurname("Иванов");

        Customer customer = DTOEntityConverter.convertToEntity(customerDTO);

        assertEquals("Иван", customer.getName());
    }

    @Test
    public void testConvertOrderEntityToDTO() {
        Order order = new Order();
        order.setId(1L);
        order.setProductId(2L);
        order.setCustomerId(3L);
        order.setOrderDate(LocalDate.parse("2023-02-10"));
        order.setQuantity(5);

        OrderDTO orderDTO = DTOEntityConverter.convertToDTO(order);

        assertEquals(1L, orderDTO.getId());
        assertEquals(2L, orderDTO.getProductId());
        assertEquals(3L, orderDTO.getCustomerId());
        assertEquals(5, orderDTO.getQuantity());
    }

    @Test
    public void testConvertProductEntityToDTO() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Молоко");
        product.setCost(1000);

        ProductDTO productDTO = DTOEntityConverter.convertToDTO(product);

        assertEquals(1L, productDTO.getId());
        assertEquals("Молоко", productDTO.getDescription());
        assertEquals(1000, productDTO.getCost());
    }

    @Test
    public void testConvertCustomerEntityToDTO() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Иван");
        customer.setSurname("Иванов");

        CustomerDTO customerDTO = DTOEntityConverter.convertToDTO(customer);

        assertEquals(1L, customerDTO.getId());
        assertEquals("Иван", customerDTO.getName());
    }
}