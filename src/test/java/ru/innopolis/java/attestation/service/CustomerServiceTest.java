package ru.innopolis.java.attestation.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innopolis.java.attestation.dto.CustomerDTO;
import ru.innopolis.java.attestation.model.Customer;
import ru.innopolis.java.attestation.repository.CustomerRepository;
import ru.innopolis.java.attestation.utils.DTOEntityConverter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void createCustomer() {
        Customer customer = new Customer();
        customer.setName("Иван");
        customer.setSurname("Иванов");


        CustomerDTO savedCustomer = customerService.createCustomer(DTOEntityConverter.convertToDTO(customer));

        assertNotNull(savedCustomer);
        assertEquals("Иван", savedCustomer.getName());
        assertEquals("Иванов", savedCustomer.getSurname());

        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());
        assertTrue(foundCustomer.isPresent());
        assertEquals("Иван", foundCustomer.get().getName());
        assertEquals("Иванов", foundCustomer.get().getSurname());
    }

    @Test
    public void getCustomerById() {
        Customer customer = new Customer();
        customer.setName("Иван");
        customer.setSurname("Иванов");
        Customer savedCustomer = customerRepository.save(customer);

        Optional<CustomerDTO> foundCustomer = customerService.getCustomerById(savedCustomer.getId());

        assertTrue(foundCustomer.isPresent());
        assertEquals("Иван", foundCustomer.get().getName());
        assertEquals("Иванов", foundCustomer.get().getSurname());
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setName("Иван");
        customer.setSurname("Иванов");
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
        updatedCustomerDTO.setName("Анна");
        updatedCustomerDTO.setSurname("Иванова");

        CustomerDTO updatedCustomer = customerService.updateCustomer(savedCustomer.getId(), updatedCustomerDTO);

        assertNotNull(updatedCustomer);
        assertEquals("Анна", updatedCustomer.getName());
        assertEquals("Иванова", updatedCustomer.getSurname());

        Optional<Customer> foundCustomer = customerRepository.findById(updatedCustomer.getId());
        assertTrue(foundCustomer.isPresent());
        assertEquals("Анна", foundCustomer.get().getName());
        assertEquals("Иванова", foundCustomer.get().getSurname());
    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer();
        customer.setName("Иван");
        customer.setSurname("Иванов");
        Customer savedCustomer = customerRepository.save(customer);
        customerService.deleteCustomer(savedCustomer.getId());

        Optional<Customer> deletedCustomer = customerRepository.findById(savedCustomer.getId());
        assertTrue(deletedCustomer.isEmpty());
    }
}
