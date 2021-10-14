package com.graphql.graphqlsql.config;


import graphql.language.StringValue;
import graphql.schema.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailScalar {

//    @Bean
//    public GraphQLScalarType email(){
//    return new GraphQLScalarType("Email", "Custome Email Description", new Coercing() {
//        @Override
//        public Object serialize(@NotNull Object o) throws CoercingSerializeException {
//            if(o instanceof StringValue){
//                return o.toString();
//            }
//            throw CoercingSerializeException.newCoercingSerializeException().message("Email is not valid").build();
//        }
//
//        @NotNull
//        @Override
//        public Object parseValue(@NotNull Object o) throws CoercingParseValueException {
//            if(o instanceof String){
//                return o.toString();
//            }
//            throw CoercingParseValueException.newCoercingParseValueException().message("Email is not valid").build();
//        }
//
//        @NotNull
//        @Override
//        public Object parseLiteral(@NotNull Object o) throws CoercingParseLiteralException {
//            if(o instanceof StringValue){
//                return o.toString();
//            }
//           throw new CoercingParseLiteralException("Email is not valid!");
//        }
//    });
//
//    }
}
