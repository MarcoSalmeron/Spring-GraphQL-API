package com.futuro_digital.spring_graphql_postgres_api.Mapper;

import com.futuro_digital.spring_graphql_postgres_api.DTO.ProductInput;
import com.futuro_digital.spring_graphql_postgres_api.Entity.Product;

public class Mapper {

    public static Product toEntity(ProductInput dto) {
        return Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .quantity(dto.quantity())
                .build();
    }
}
