package com.ecommerce.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrinho {

    private long clienteId;
    private List<ItemPedido> itens = new ArrayList<>();
    private BigDecimal valorTotal;
}
