package ru.innopolis.java.attestation.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderDTO {
    private Long id;
    private Long productId;
    private Long customerId;
    private LocalDate orderDate;
    private int quantity;
}
