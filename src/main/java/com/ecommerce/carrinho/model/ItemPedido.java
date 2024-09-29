package com.ecommerce.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedido {

    private long produtoId;
    private int quantidade;
    private BigDecimal valorUnitario;
}
