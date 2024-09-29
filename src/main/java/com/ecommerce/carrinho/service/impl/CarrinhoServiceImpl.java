package com.ecommerce.carrinho.service.impl;

import com.ecommerce.carrinho.model.*;
import com.ecommerce.carrinho.service.CarrinhoService;
import com.ecommerce.carrinho.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {

    private final HttpSession session;
    private final PedidoService pedidoService;
    private static final String CARRINHO_ATTR = "carrinho";

    @Override
    public Carrinho criarCarrinho(Long clienteId) {
        Carrinho carrinho = new Carrinho();
        carrinho.setClienteId(clienteId);
        session.setAttribute(CARRINHO_ATTR, carrinho);
        return carrinho;
    }

    @Override
    public Carrinho adicionarItem(ItemPedido itemPedido) {
        Carrinho carrinho = getCarrinho();
        carrinho.getItens().add(itemPedido);
        carrinho.setValorTotal(carrinho.getItens().stream()
                .map(i -> i.getValorUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        session.setAttribute(CARRINHO_ATTR, carrinho);
        return carrinho;
    }

    @Override
    public Carrinho removerItem(Long produtoId) {
        Carrinho carrinho = getCarrinho();
        if (carrinho != null) {
            carrinho.setItens(carrinho.getItens().stream()
                    .filter(i -> i.getProdutoId() != produtoId)
                    .collect(Collectors.toList()));
            carrinho.setValorTotal(carrinho.getItens().stream()
                    .map(i -> i.getValorUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            session.setAttribute(CARRINHO_ATTR, carrinho);
        }
        return carrinho;
    }

    @Override
    public Carrinho finalizarCarrinho() {
        Carrinho carrinho = getCarrinho();
        if (carrinho != null) {
            Pedido pedido = converterCarrinhoParaPedido(carrinho);
            pedidoService.criarPedido(pedido);
            session.removeAttribute(CARRINHO_ATTR);
        }
        return carrinho;
    }

    @Override
    public Optional<Carrinho> buscarCarrinho() {
        return Optional.ofNullable((Carrinho) session.getAttribute(CARRINHO_ATTR));
    }

    @Override
    public void limparCarrinho() {
        session.removeAttribute(CARRINHO_ATTR);
    }

    private Carrinho getCarrinho() {
        return (Carrinho) session.getAttribute(CARRINHO_ATTR);
    }

    private Pedido converterCarrinhoParaPedido(Carrinho carrinho) {
        Cliente cliente = Cliente.builder()
                .id(carrinho.getClienteId())
                .build();

        List<ItemPedido> itensPedido = carrinho.getItens().stream()
                .map(i -> ItemPedido.builder()
                        .produtoId(i.getProdutoId())
                        .quantidade(i.getQuantidade())
                        .valorUnitario(i.getValorUnitario())
                        .build())
                .collect(Collectors.toList());

        return Pedido.builder()
                .dataPedido(LocalDateTime.now())
                .status(StatusPedido.PROCESSANDO)
                .valorTotal(carrinho.getValorTotal())
                .cliente(cliente)
                .itensPedido(itensPedido)
                .build();
    }
}
