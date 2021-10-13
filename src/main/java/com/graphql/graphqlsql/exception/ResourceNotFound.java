package com.graphql.graphqlsql.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class ResourceNotFound  extends RuntimeException implements GraphQLError {

    private final String message;


    public ResourceNotFound(String msg){
        super(msg);
        this.message =msg;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public List<Object> getPath() {
        return GraphQLError.super.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return GraphQLError.super.toSpecification();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return GraphQLError.super.getExtensions();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        /*
        to prevent graphql from passing the stacktraceError to
        the client side
         */
        return null;
    }
}
