package dev.petrauskas.wishlist.entrypoint.mapper;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoMapperTest {

    private ProdutoMapper mapper = Mappers.getMapper(ProdutoMapper.class);
    private Produto produtoFixture;
    private ProdutoDto dtoFixture;
    private UUID uuid;

    @BeforeEach
    void setup() {
        uuid = UUID.randomUUID();

        produtoFixture = Produto.builder()
                .idCliente(uuid)
                .idProduto(uuid)
                .nome("Nome produto")
                .build();

        dtoFixture = ProdutoDto.builder().idProduto(uuid)
                .idCliente(uuid)
                .nome("Nome produto")
                .build();
    }

    @Test
    void deveMapearParaDtoCorretamente() {
        ProdutoDto resultado = mapper.toDto(produtoFixture);
        assertEquals(dtoFixture, resultado);
    }

    @Test
    void deveMapearParaDomainCorretamente() {
        Produto resultado = mapper.toDomain(dtoFixture, uuid);
        assertEquals(produtoFixture, resultado);
    }
}