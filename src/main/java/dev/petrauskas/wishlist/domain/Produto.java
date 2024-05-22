package dev.petrauskas.wishlist.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
public class Produto {
    private UUID idCliente;
    private UUID idProduto;
    private String nome;
}
