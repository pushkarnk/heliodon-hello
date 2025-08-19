package com.example.helidon.hello;

import io.helidon.config.Config;
import io.helidon.logging.common.LogConfig;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

import com.example.helidon.hello.HelloService;

public class Main {
    
    private Main() {
    }

    public static void main(String[] args) {
        LogConfig.configureRuntime();
        
        Config config = Config.create();
        Config.global(config);
        
        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(Main::routing)
                .build()
                .start();

        System.out.println("Helidon Hello World with JPMS started!");
        System.out.println("Server running at: http://localhost:" + server.port());
    }

    private static void routing(HttpRouting.Builder rules) {
        HelloService helloService = new HelloService();
        
        rules.get("/", (req, res) -> {
            res.send("""
                {
                    "message": "Hello World from Helidon with JPMS!",
                    "framework": "Helidon SE",
                    "module": "%s",
                    "javaVersion": "%s",
                    "runtime": "Custom JLink Runtime"
                }
                """.formatted(
                    Main.class.getModule().getName(),
                    System.getProperty("java.version")
            ));
        })
        .get("/hello", helloService::hello)
        .get("/hello/{name}", helloService::helloWithName);
    }
}

