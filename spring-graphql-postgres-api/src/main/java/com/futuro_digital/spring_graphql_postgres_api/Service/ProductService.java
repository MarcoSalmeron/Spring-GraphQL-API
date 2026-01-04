package com.futuro_digital.spring_graphql_postgres_api.Service;

import com.futuro_digital.spring_graphql_postgres_api.Entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> filterProductsByPrice(Double price);

    Product getProductById(Long id);

    Product save(Product product);

    Product update(Long id, Product productDetails);

    void deleteById(Long id);
}
