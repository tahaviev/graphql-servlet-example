package com.github.tahaviev.graphql;

import graphql.GraphQL;
import java.util.Map;
import org.junit.jupiter.api.Test;

public final class MyGraphQLSchemaTest {

    @Test
    public void works() {
        assert GraphQL
            .newGraphQL(new MyGraphQLSchema().get())
            .build()
            .execute("{entity(id: \"45\") {name id age}}")
            .<Map<String, Map<String, String>>>getData()
            .get("entity")
            .get("id")
            .equals("45");
    }
}
