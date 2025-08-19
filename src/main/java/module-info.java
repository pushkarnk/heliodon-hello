module com.example.helidon.hello {
    requires io.helidon.webserver;
    requires io.helidon.logging.common;
    requires org.eclipse.parsson;
    requires io.helidon.http.media.jsonp;

    requires jakarta.json;

    exports com.example.helidon.hello;
}
