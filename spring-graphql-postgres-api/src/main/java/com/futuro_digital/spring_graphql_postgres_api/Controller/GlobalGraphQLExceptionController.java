package com.futuro_digital.spring_graphql_postgres_api.Controller;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalGraphQLExceptionController {

    @GraphQlExceptionHandler(EntityNotFoundException.class)
    public GraphQLError handleEntityNotFound(EntityNotFoundException ex) {
        log.warn("[WARNING] Ejecutando Exception Handler: "+ex.getClass());
        return GraphqlErrorBuilder.newError()
                .message("Product not found: "+ex.getMessage())              // Mensaje de la excepción
                .errorType(ErrorType.NOT_FOUND)        // Tipo de error semántico
                .build();
    }

    @GraphQlExceptionHandler(IllegalArgumentException.class)
    public GraphQLError handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("[WARNING] Ejecutando Exception Handler: "+ex.getClass());
        return GraphqlErrorBuilder.newError()
                .message("Invalid argument: " + ex.getMessage())
                .errorType(ErrorType.BAD_REQUEST)
                .build();
    }

    @GraphQlExceptionHandler(MethodArgumentNotValidException.class)
    public GraphQLError handleValidation(MethodArgumentNotValidException ex) {
        log.warn("[WARNING] Ejecutando Exception Handler: "+ex.getClass());
        return GraphqlErrorBuilder.newError()
                .message("Validation error: " + ex.getMessage())
                .errorType(ErrorType.BAD_REQUEST)
                .build();
    }

    @GraphQlExceptionHandler({TypeMismatchException.class, MethodArgumentTypeMismatchException.class})
    public GraphQLError handleTypeMismatch(Exception ex) {
        log.warn("[WARNING] Ejecutando Exception Handler: "+ex.getClass());
        return GraphqlErrorBuilder.newError()
                .message("Type mismatch error: " + ex.getMessage())
                .errorType(ErrorType.BAD_REQUEST)
                .build();
    }

    @GraphQlExceptionHandler(ConstraintViolationException.class)
    public GraphQLError handleConstraintViolation(ConstraintViolationException ex) {
        log.warn("[WARNING] Ejecutando Exception Handler: "+ex.getClass());
        // Concatenamos todos los mensajes de validación
        String errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        return GraphqlErrorBuilder.newError()
                .message("Validation error(s): " + errors)
                .errorType(ErrorType.BAD_REQUEST)
                .build();
    }

}
