package com.github.tahaviev.graphql;

import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import java.util.List;
import java.util.function.Supplier;

public final class MyGraphQLSchema implements Supplier<GraphQLSchema> {

    @Override
    public GraphQLSchema get() {
        return SchemaParser
            .newParser()
            .file("schema.graphql")
            .resolvers(new MyEntitiesQueryResolver(), new MyEntityQueryResolver())
            .build()
            .makeExecutableSchema();
    }

    public static final class MyEntitiesQueryResolver implements EntitiesQueryResolver {

        @Override
        public List<Entity> entities() {
            return List.of(
                new Entity("id1", 1, "name1"),
                new Entity("id2", 2, "name2")
            );
        }

    }

    public static final class MyEntityQueryResolver implements EntityQueryResolver {

        @Override
        public Entity entity(final String id) {
            return new Entity(String.valueOf(id), 0, "unnamed");
        }

    }

}
