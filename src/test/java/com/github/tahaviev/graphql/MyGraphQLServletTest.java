package com.github.tahaviev.graphql;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.jupiter.api.Test;

public final class MyGraphQLServletTest {

    @Test
    public void works() throws Exception {
        final var server = new Server(8080) {{
            setHandler(
                new ServletContextHandler() {{
                    addServlet(
                        new ServletHolder(new MyGraphQLServlet()), "/"
                    );
                }}
            );
        }};
        try {
            server.start();
            final var response = HttpClient.newHttpClient().send(
                HttpRequest
                    .newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString("query {entities {id}}"))
                    .header("Content-Type", "application/graphql")
                    .uri(new URI("http://localhost:8080"))
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            );
            assert response.body().contains("id2");
        } finally {
            server.stop();
        }
    }
}
