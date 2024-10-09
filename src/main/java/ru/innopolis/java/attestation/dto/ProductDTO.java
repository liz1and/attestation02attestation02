package ru.innopolis.java.attestation.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String description;
    private double cost;
    private int quantity;
}
