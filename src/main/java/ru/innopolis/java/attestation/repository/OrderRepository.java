package ru.innopolis.java.attestation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.java.attestation.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
