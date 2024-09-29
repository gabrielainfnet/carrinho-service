package com.ecommerce.carrinho.controller;

import com.ecommerce.carrinho.model.Carrinho;
import com.ecommerce.carrinho.model.ItemPedido;
import com.ecommerce.carrinho.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping("/criar")
    public ResponseEntity<Carrinho> criarCarrinho(@RequestBody Long clienteId) {
        Carrinho carrinho = carrinhoService.criarCarrinho(clienteId);
        return ResponseEntity.ok(carrinho);
    }
    @GetMapping
    public ResponseEntity<Optional<Carrinho>> buscarCarrinho() {
        Optional<Carrinho> optCarrinho = carrinhoService.buscarCarrinho();
        return ResponseEntity.ok(optCarrinho);
    }

    @PostMapping("/adicionar-item")
    public ResponseEntity<Carrinho> adicionarItem(@RequestBody ItemPedido itemPedido) {
        Carrinho carrinho = carrinhoService.adicionarItem(itemPedido);
        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping("/remover-item/{idProduto}")
    public ResponseEntity<Carrinho> removerItem(@PathVariable("idProduto") Long idProduto) {
        Carrinho carrinho = carrinhoService.removerItem(idProduto);
        return ResponseEntity.ok(carrinho);
    }

    @PostMapping("/finalizar")
    public ResponseEntity<Carrinho> finalizarCarrinho() {
        Carrinho carrinho = carrinhoService.finalizarCarrinho();
        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping("/limpar")
    public ResponseEntity<Void> limparCarrinho() {
        carrinhoService.limparCarrinho();
        return ResponseEntity.noContent().build();
    }
}
