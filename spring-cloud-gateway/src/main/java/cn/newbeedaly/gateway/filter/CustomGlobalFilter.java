package cn.newbeedaly.gateway.filter;


import cn.newbeedaly.gateway.adaptor.user.UserFeignClient;
import com.google.common.net.HttpHeaders;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Value("#{'/user/login,/user/getServerName,/actuator/**'.split(',')}")
    private List<String> ignoreUrls;

    @Lazy
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("gateway filter...");
        ServerHttpRequest request = exchange.getRequest();
        if (ignoreUrls != null && ignoreUrls.contains(request.getURI().getPath())) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            return onError(exchange, "尚未登录");
        }
        // WebFlux异步调用，同步会报错
        Future<Boolean> future = CompletableFuture.supplyAsync(() -> userFeignClient.validToken(token));
        try {
            Boolean b = future.get();
            if (b) {
                return chain.filter(exchange.mutate().request(request).build());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return onError(exchange, "valid user token error");
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer dataBuffer = response.bufferFactory().wrap(msg.getBytes());
        return response.writeWith(Flux.just(dataBuffer));
    }

}
