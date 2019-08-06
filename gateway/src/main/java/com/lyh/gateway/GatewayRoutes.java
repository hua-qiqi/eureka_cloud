package com.lyh.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->
                        r.path("/user/**")
                                .filters(f -> f.stripPrefix(1))
                                .uri("lb://feign-provider"))
                .route(r ->
                        r.path("/a/**")
                                .filters(f -> f.hystrix(c -> c.setFallbackUri("forward:/fallback")))
                                .uri("lb://feign-provider"))
                .route(r ->
                        r.path("/time/**")
                                .filters(f -> f.filter(new RequestTimeFilter()))

                                .uri("lb://feign-provider")
                                .order(0)
                                .id("customer_filter_router"))
                .build();
    }


}
