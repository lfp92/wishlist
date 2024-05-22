package dev.petrauskas.wishlist.gateway.mapper;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.entities.ProdutoClienteEntity;
import dev.petrauskas.wishlist.gateway.entities.ProdutoClienteEntityKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoEntityMapperTest {

    private ProdutoEntityMapper mapper = Mappers.getMapper(ProdutoEntityMapper.class);
    private UUID uuid;
    private Produto produtoFixture;
    private ProdutoClienteEntity entityFixture;

    @BeforeEach
    void setup() {
        uuid = UUID.randomUUID();

        produtoFixture = Produto.builder()
                .idCliente(uuid)
                .idProduto(uuid)
                .nome("Nome produto")
                .build();

        entityFixture = ProdutoClienteEntity.builder()
                .key(ProdutoClienteEntityKey.builder()
                        .idCliente(uuid)
                        .idProduto(uuid)
                        .build())
                .nomeProduto("Nome produto")
                .build();
    }

    @Test
    void deveMapearParaEntityCorretamente() {
        ProdutoClienteEntity resultado = mapper.toEntity(produtoFixture);
        assertEquals(entityFixture, resultado);
    }

    @Test
    void deveMapearParaDomainCorretamente() {
        Produto resultado = mapper.toDomain(entityFixture);
        assertEquals(produtoFixture, resultado);
    }

}