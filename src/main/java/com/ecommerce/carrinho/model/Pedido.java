package com.ecommerce.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;
    private StatusPedido status;
    private BigDecimal valorTotal;
    private Cliente cliente;
    private Endereco enderecoEntrega;
    private List<ItemPedido> itensPedido = new ArrayList<>();
}
