package com.ecommerce.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private long id;
    private String nome;
    private String email;
    private List<Endereco> enderecos;
}
