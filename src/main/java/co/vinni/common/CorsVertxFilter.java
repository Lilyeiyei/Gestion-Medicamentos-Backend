package co.vinni.common;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CorsVertxFilter {

    @RouteFilter(400)
    void corsFilter(RoutingContext rc) {
        rc.response()
                .putHeader("Access-Control-Allow-Origin", "*")
                .putHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH")
                .putHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, Accept, Origin")
                .putHeader("Access-Control-Max-Age", "86400");

        if (rc.request().method().name().equalsIgnoreCase("OPTIONS")) {
            rc.response().setStatusCode(200).end();
            return;
        }

        rc.next();
    }
}