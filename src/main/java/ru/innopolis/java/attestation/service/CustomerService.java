package ru.innopolis.java.attestation.service;

import jakarta.persistence.EntityNotFoundException;
import ru.innopolis.java.attestation.dto.CustomerDTO;
import ru.innopolis.java.attestation.model.Customer;
import ru.innopolis.java.attestation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation.utils.DTOEntityConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(DTOEntityConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(DTOEntityConverter::convertToDTO);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = DTOEntityConverter.convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return DTOEntityConverter.convertToDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(customerDTO.getName());
            Customer updatedCustomer = customerRepository.save(customer);
            return DTOEntityConverter.convertToDTO(updatedCustomer);
        } else {
            throw new EntityNotFoundException("Customer not found");
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

