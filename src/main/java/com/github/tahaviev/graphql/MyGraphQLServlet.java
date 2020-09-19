package com.github.tahaviev.graphql;

import graphql.kickstart.servlet.DefaultGraphQLServlet;
import graphql.kickstart.servlet.input.GraphQLInvocationInputFactory;

public final class MyGraphQLServlet extends DefaultGraphQLServlet {

    @Override
    protected GraphQLInvocationInputFactory getInvocationInputFactory() {
        return GraphQLInvocationInputFactory.newBuilder(new MyGraphQLSchema().get()).build();
    }

}
