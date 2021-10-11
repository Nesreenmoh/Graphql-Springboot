package com.graphql.graphqlsql.resolver.author;

import com.graphql.graphqlsql.TestApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
class AuthorQueryResolverTest {

    @Autowired
    GraphQLTestTemplate  graphQLTestTemplate;


    @Test
    public void shouldAbleToGetAuthorData() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.
                postForResource("request/author-query.graphqls");
        assertThat(graphQLResponse.isOk(),equalTo(true));

        assertEquals(FileReaderUtil.read("response/author-query.json")
                ,graphQLResponse.getRawResponse().getBody(),true);
    }

}