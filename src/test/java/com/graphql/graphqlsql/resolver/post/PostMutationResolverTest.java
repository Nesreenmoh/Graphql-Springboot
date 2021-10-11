package com.graphql.graphqlsql.resolver.post;

import com.graphql.graphqlsql.TestApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
class PostMutationResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;


    @Test
    public void shouldAbleToCreatePost() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.
                postForResource("request/create-post-mutation.graphqls");
        assertThat(graphQLResponse.isOk(),equalTo(true));
        String  id= graphQLResponse.get("$.data.createPost");
        assertNotNull(id);
        assertThat(id,is(notNullValue()));
    }
}