package com.devyourid.hoardr.api.utility;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLExceptionHandler {

    // Propagates custom exception
    @GraphQlExceptionHandler(SetNotFoundException.class)
    public GraphQLError handleSetNotFound(RuntimeException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    @GraphQlExceptionHandler(CollectionNotFoundException.class)
    public GraphQLError handleCollectionNotFound(RuntimeException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    @GraphQlExceptionHandler(CardNotFoundException.class)
    public GraphQLError handleCardNotFound(RuntimeException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    // Custom Exception
    public static class SetNotFoundException extends RuntimeException {
        public SetNotFoundException(String id) {
            super("Set not found");
        }
    }

    // Custom Exception
    public static class CollectionNotFoundException extends RuntimeException {
        public CollectionNotFoundException(String id) {
            super("Set not found");
        }
    }

    // Custom Exception
    public static class CardNotFoundException extends RuntimeException {
        public CardNotFoundException(String id) {
            super("Card not found");
        }
    }
}

