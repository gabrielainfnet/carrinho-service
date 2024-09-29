package com.ecommerce.carrinho.service;

import com.ecommerce.carrinho.model.Carrinho;
import com.ecommerce.carrinho.model.ItemPedido;

import java.util.Optional;


public interface CarrinhoService {

    Carrinho adicionarItem(ItemPedido itemPedido);

    Carrinho criarCarrinho(Long clienteId);

    Carrinho removerItem(Long produtoId);

    Carrinho finalizarCarrinho();

    Optional<Carrinho> buscarCarrinho();

    void limparCarrinho();
}
