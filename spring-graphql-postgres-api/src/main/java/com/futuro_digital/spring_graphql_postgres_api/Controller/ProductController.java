package com.futuro_digital.spring_graphql_postgres_api.Controller;

import com.futuro_digital.spring_graphql_postgres_api.DTO.ProductInput;
import com.futuro_digital.spring_graphql_postgres_api.Entity.Product;
import com.futuro_digital.spring_graphql_postgres_api.Mapper.Mapper;
import com.futuro_digital.spring_graphql_postgres_api.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @QueryMapping
    public List<Product> findAllProducts() {
        log.info("Ejecutando Query [ findAllProducts ]");
        return productService.getAllProducts();
    }

    @QueryMapping
    public List<Product> filterProductsByPrice(@Argument Double price) {
        log.info("Ejecutando Query [ filterProductsByPrice ]");
        return productService.filterProductsByPrice(price);
    }

    @QueryMapping
    public Product findProductById(@Argument Long id) {
        log.info("Ejecutando Query [ findProductById ]");
        return productService.getProductById(id);
    }

    @MutationMapping
    public Product createProduct(@Valid @Argument ProductInput productInput) {
        log.info("Ejecutando Mutation [ createProduct ]");
        return productService.save(Mapper.toEntity(productInput));
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Valid @Argument ProductInput productInput) {
        log.info("Ejecutando Mutation [ updateProduct ]");
        return productService.update(id, Mapper.toEntity(productInput));
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        log.info("Ejecutando Mutation [ deleteProduct ]");
        productService.deleteById(id);
        return true;
    }
}
