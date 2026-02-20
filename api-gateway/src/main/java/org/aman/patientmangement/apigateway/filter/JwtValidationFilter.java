package org.aman.patientmangement.apigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import java.util.List;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtValidationFilter implements WebFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        System.out.println("🔥 JwtValidationFilter HIT");



        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8));

        Key key = Keys.hmacShaKeyFor(keyBytes);



        String path = exchange.getRequest().getURI().getPath();

        System.out.println("PATH = " + path);
        System.out.println("AUTH HEADER = " +
                exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        // Allow auth endpoints
        if (path.startsWith("/auth/")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();


            // 🔹 Extract identity
            String userId = claims.getSubject();
            String role = claims.get("role", String.class);
            String email = claims.get("email", String.class);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    );

            // 🔹 IMPORTANT: strip any incoming fake headers
            ServerHttpRequest mutatedRequest = exchange.getRequest()
                    .mutate()
                    .headers(headers -> {
                        headers.remove("X-Internal-User-Id");
                        headers.remove("X-Internal-User-Role");
                        headers.remove("X-Internal-Email");

                        // 🔹 Add trusted headers
                        headers.add("X-Internal-User-Id", userId);
                        headers.add("X-Internal-User-Role", role);
                        headers.add("X-Internal-Email", email);
                    })
                    .build();

            return chain.filter(
                    exchange.mutate().request(mutatedRequest).build()
            ).contextWrite(
                    ReactiveSecurityContextHolder.withAuthentication(authentication)
            );

        } catch (Exception e) {
            e.printStackTrace();
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}

