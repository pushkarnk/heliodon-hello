package com.example.helidon.hello;

import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import java.util.Collections;
import java.util.Set;

public class HelloService {
    
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    public void hello(ServerRequest request, ServerResponse response) {
        JsonObject jsonObject = JSON.createObjectBuilder()
                .add("message", "Hello World!")
                .add("framework", "Helidon SE with JPMS")
                .add("timestamp", System.currentTimeMillis())
                .build();
        response.send(jsonObject);
    }

    public void helloWithName(ServerRequest request, ServerResponse response) {
        String name = request.path().pathParameters().get("name");
        
        JsonObject jsonObject = JSON.createObjectBuilder()
                .add("message", "Hello " + name + "!")
                .add("framework", "Helidon SE with JPMS")
                .add("timestamp", System.currentTimeMillis())
                .build();
        response.send(jsonObject);
    }
}

