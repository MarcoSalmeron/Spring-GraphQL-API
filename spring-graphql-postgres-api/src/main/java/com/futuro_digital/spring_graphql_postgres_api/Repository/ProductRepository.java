package com.futuro_digital.spring_graphql_postgres_api.Repository;

import com.futuro_digital.spring_graphql_postgres_api.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByPriceLessThanEqual(Double price);
}
