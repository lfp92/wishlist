package dev.petrauskas.wishlist.gateway.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Builder
@Table(value = "produtos_cliente")
@EqualsAndHashCode
public class ProdutoClienteEntity {

    @PrimaryKey
    private final ProdutoClienteEntityKey key;

    @Column(value = "nome_produto")
    private String nomeProduto;
}
