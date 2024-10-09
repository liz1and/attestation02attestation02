package ru.innopolis.java.attestation.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.innopolis.java.attestation.model.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void saveProduct() {

        Product product = new Product();
        product.setDescription("Молоко");
        product.setCost(10.0);
        product.setQuantity(100);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertEquals("Молоко", savedProduct.getDescription());
        assertEquals(10.0, savedProduct.getCost(), 0.01);
        assertEquals(100, savedProduct.getQuantity());
    }

    @Test
    public void findAllProducts() {

        Product product1 = new Product();
        product1.setDescription("Молоко");
        product1.setCost(10.0);
        product1.setQuantity(100);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setDescription("Кофе");
        product2.setCost(15.0);
        product2.setQuantity(50);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();

        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    public void findProductById() {

        Product product = new Product();
        product.setDescription("Молоко");
        product.setCost(10.0);
        product.setQuantity(100);
        Product savedProduct = productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals("Молоко", foundProduct.get().getDescription());
        assertEquals(10.0, foundProduct.get().getCost(), 0.01);
        assertEquals(100, foundProduct.get().getQuantity());
    }

    @Test
    public void updateProduct() {

        Product product = new Product();
        product.setDescription("Молоко");
        product.setCost(10.0);
        product.setQuantity(100);
        Product savedProduct = productRepository.save(product);

        savedProduct.setDescription("Молоко 2");
        savedProduct.setCost(12.0);
        savedProduct.setQuantity(120);

        Product updatedProduct = productRepository.save(savedProduct);

        assertNotNull(updatedProduct);
        assertEquals("Молоко 2", updatedProduct.getDescription());
        assertEquals(12.0, updatedProduct.getCost(), 0.01);
        assertEquals(120, updatedProduct.getQuantity());
    }

    @Test
    public void deleteProduct() {

        Product product = new Product();
        product.setDescription("Молоко");
        product.setCost(10.0);
        product.setQuantity(100);
        Product savedProduct = productRepository.save(product);

        productRepository.delete(savedProduct);
        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());

        assertTrue(deletedProduct.isEmpty());
    }
}
