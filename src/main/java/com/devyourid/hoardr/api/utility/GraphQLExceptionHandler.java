package com.devyourid.hoardr.api.utility;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLExceptionHandler {

    @GraphQlExceptionHandler(NotFoundException.class)
    public GraphQLError handleNotFound(NotFoundException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    // Not Found Super Class
    public abstract static class NotFoundException extends RuntimeException {
        protected NotFoundException(String message) {
            super(message);
        }
    }

    // Specified Not Found Child Classes

    public static class SeriesNotFoundException extends NotFoundException {
        public SeriesNotFoundException(String id) {
            super("Series not found: " + id);
        }
    }

    public static class ExpansionSetNotFoundException extends NotFoundException {
        public ExpansionSetNotFoundException(String id) {
            super("Expansion Set not found: " + id);
        }
    }

    public static class CardNotFoundException extends NotFoundException {
        public CardNotFoundException(String id) {
            super("Card not found: " + id);
        }
    }
}

