package com.ecommerce.carrinho.service;

import com.ecommerce.carrinho.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final WebClient.Builder webClientBuilder;

    public void criarPedido(Pedido pedido) {
        WebClient webClient = webClientBuilder.build();

        webClient.post()
                .uri("http://pedido-service/")
                .body(Mono.just(pedido), Pedido.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
