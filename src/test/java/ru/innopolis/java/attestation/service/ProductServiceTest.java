package ru.innopolis.java.attestation.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innopolis.java.attestation.dto.ProductDTO;
import ru.innopolis.java.attestation.model.Product;
import ru.innopolis.java.attestation.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getAllProducts() {
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

        List<ProductDTO> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Молоко", products.get(0).getDescription());
        assertEquals(10.0, products.get(0).getCost(), 0.01);
        assertEquals(100, products.get(0).getQuantity());
        assertEquals("Кофе", products.get(1).getDescription());
        assertEquals(15.0, products.get(1).getCost(), 0.01);
        assertEquals(50, products.get(1).getQuantity());
    }

    @Test
    public void getProductById() {
        Product product = new Product();
        product.setDescription("Молоко");
        product.setCost(10.0);
        product.setQuantity(100);
        Product savedProduct = productRepository.save(product);

        Optional<ProductDTO> foundProduct = productService.getProductById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals("Молоко", foundProduct.get().getDescription());
        assertEquals(10.0, foundProduct.get().getCost(), 0.01);
        assertEquals(100, foundProduct.get().getQuantity());
    }

    @Test
    public void createProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("Торт");
        productDTO.setCost(20.0);
        productDTO.setQuantity(75);

        ProductDTO createdProduct = productService.createProduct(productDTO);

        assertNotNull(createdProduct);
        assertEquals("Торт", createdProduct.getDescription());
        assertEquals(20.0, createdProduct.getCost(), 0.01);
        assertEquals(75, createdProduct.getQuantity());

        Optional<Product> foundProduct = productRepository.findById(createdProduct.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Торт", foundProduct.get().getDescription());
        assertEquals(20.0, foundProduct.get().getCost(), 0.01);
        assertEquals(75, foundProduct.get().getQuantity());
    }

    @Test
    public void updateProduct() {
        Product product = new Product();
        product.setDescription("Молоко");
        product.setCost(10.0);
        product.setQuantity(100);
        Product savedProduct = productRepository.save(product);

        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setDescription("Молоко 2");
        updatedProductDTO.setCost(15.0);
        updatedProductDTO.setQuantity(150);

        ProductDTO updatedProduct = productService.updateProduct(savedProduct.getId(), updatedProductDTO);

        assertNotNull(updatedProduct);
        assertEquals("Молоко 2", updatedProduct.getDescription());
        assertEquals(15.0, updatedProduct.getCost(), 0.01);
        assertEquals(150, updatedProduct.getQuantity());

        Optional<Product> foundProduct = productRepository.findById(updatedProduct.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Updated Product A", foundProduct.get().getDescription());
        assertEquals(15.0, foundProduct.get().getCost(), 0.01);
        assertEquals(150, foundProduct.get().getQuantity());
    }

    @Test
    public void deleteProduct() {
        Product product = new Product();
        product.setDescription("Молоко");
        product.setCost(10.0);
        product.setQuantity(100);
        Product savedProduct = productRepository.save(product);

        productService.deleteProduct(savedProduct.getId());

        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
        assertTrue(deletedProduct.isEmpty());
    }
}

