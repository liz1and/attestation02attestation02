package ru.innopolis.java.attestation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.java.attestation.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
