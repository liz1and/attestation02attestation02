package ru.innopolis.java.attestation.service;

import jakarta.persistence.EntityNotFoundException;
import ru.innopolis.java.attestation.dto.ProductDTO;
import ru.innopolis.java.attestation.model.Product;
import ru.innopolis.java.attestation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation.utils.DTOEntityConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(DTOEntityConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(DTOEntityConverter::convertToDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = DTOEntityConverter.convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);

        return DTOEntityConverter.convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setDescription(productDTO.getDescription());
            product.setCost(productDTO.getCost());
            product.setQuantity(productDTO.getQuantity());
            Product updatedProduct = productRepository.save(product);
            return DTOEntityConverter.convertToDTO(updatedProduct);
        } else {
            throw new EntityNotFoundException("Product not found");
        }
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
