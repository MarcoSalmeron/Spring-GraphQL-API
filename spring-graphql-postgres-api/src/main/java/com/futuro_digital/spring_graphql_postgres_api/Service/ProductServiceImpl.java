package com.futuro_digital.spring_graphql_postgres_api.Service;

import com.futuro_digital.spring_graphql_postgres_api.Entity.Product;
import com.futuro_digital.spring_graphql_postgres_api.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> filterProductsByPrice(Double price) {
        return productRepository.findByPriceLessThanEqual(price);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("(Product) -> ID: "+id+" doesnt exist!"));
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Long id, Product productDetails) {
        Product product = this.getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if(id == null || !productRepository.existsById(id)) throw new EntityNotFoundException("(Product) -> ID: "+id+" doesnt exist! (or is null)");
        productRepository.deleteById(id);
    }
}
