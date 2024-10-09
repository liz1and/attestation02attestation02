package ru.innopolis.java.attestation.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.innopolis.java.attestation.model.Customer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void saveCustomer() {

        Customer customer = new Customer();
        customer.setName("Иван");
        customer.setSurname("Иванов");

        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer);
        assertEquals("Иван", savedCustomer.getName());
        assertEquals("Иванов", savedCustomer.getSurname());
    }

    @Test
    public void findAllCustomers() {

        Customer customer1 = new Customer();
        customer1.setName("Иван");
        customer1.setSurname("Иванов");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Анна");
        customer2.setSurname("Иванова");
        customerRepository.save(customer2);

        List<Customer> customers = customerRepository.findAll();

        assertNotNull(customers);
        assertEquals(2, customers.size());
        assertEquals("Иван", customers.get(0).getName());
        assertEquals("Иванов", customers.get(0).getSurname());
        assertEquals("Анна", customers.get(1).getName());
        assertEquals("Иванова", customers.get(1).getSurname());
    }

    @Test
    public void findCustomerById() {

        Customer customer = new Customer();
        customer.setName("Иван");
        customer.setSurname("Иванов");
        Customer savedCustomer = customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());

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
        savedCustomer.setName("Анна");
        savedCustomer.setSurname("Иванова");

        Customer updatedCustomer = customerRepository.save(savedCustomer);

        assertEquals("Анна", updatedCustomer.getName());
        assertEquals("Иванова", updatedCustomer.getSurname());
    }

    @Test
    public void deleteCustomer() {

        Customer customer = new Customer();
        customer.setName("Иван");
        customer.setSurname("Иванов");
        Customer savedCustomer = customerRepository.save(customer);

        customerRepository.delete(savedCustomer);
        Optional<Customer> deletedCustomer = customerRepository.findById(savedCustomer.getId());

        assertTrue(deletedCustomer.isEmpty());
    }
}
