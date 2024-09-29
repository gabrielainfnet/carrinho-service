package com.ecommerce.carrinho.service;

import com.ecommerce.carrinho.model.Pedido;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PedidoService {

    private String pedidoApiURL = "http://localhost:8082/";

    public void criarPedido(Pedido pedido) {
        WebClient.create(pedidoApiURL)
                .post()
                .body(Mono.just(pedido), Pedido.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
