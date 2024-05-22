package dev.petrauskas.wishlist.gateway.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.entities.ProdutoClienteEntity;
import dev.petrauskas.wishlist.gateway.entities.ProdutoClienteEntityKey;
import dev.petrauskas.wishlist.gateway.mapper.ProdutoEntityMapper;
import dev.petrauskas.wishlist.gateway.repository.ProdutoClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoGatewayImplTest {

    @InjectMocks
    private ProdutoGatewayImpl gateway;

    @Mock
    private ProdutoClienteRepository repository;

    @Mock
    private ProdutoEntityMapper mapper;

    private UUID id;
    private ProdutoClienteEntity produtoEntityFixture;
    private Produto produtoFixture;

    @BeforeEach
    void setup() {
        id = UUID.randomUUID();

        produtoEntityFixture = ProdutoClienteEntity.builder()
                .key(ProdutoClienteEntityKey.builder()
                        .idCliente(id)
                        .idProduto(id)
                        .build())
                .nomeProduto("Nome produto")
                .build();

        produtoFixture = Produto.builder()
                .idProduto(id)
                .idCliente(id)
                .nome("Nome produto")
                .build();
    }

    @Test
    void deveRetornarErroAoAdicionarProduto() {
        doThrow(RuntimeException.class)
                .when(repository)
                .save(any());

        assertThrows(RuntimeException.class, () -> gateway.adicionarProduto(produtoFixture));
        verify(repository, times(1)).save(any());
        verify(mapper, times(1)).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void deveAdicionarProdutoComSucesso() {
        assertDoesNotThrow(() -> gateway.adicionarProduto(produtoFixture));
        verify(repository, times(1)).save(any());
        verify(mapper, times(1)).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void deveRetornarErroAoRemoverProduto() {
        doThrow(RuntimeException.class)
                .when(repository)
                .deleteByKeyIdClienteAndKeyIdProduto(any(), any());

        assertThrows(RuntimeException.class, () -> gateway.removerProduto(id, id));
        verify(repository, times(1)).deleteByKeyIdClienteAndKeyIdProduto(any(), any());
        verify(mapper, never()).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        assertDoesNotThrow(() -> gateway.removerProduto(id, id));
        verify(repository, times(1)).deleteByKeyIdClienteAndKeyIdProduto(any(), any());
        verify(mapper, never()).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void deveRetornarErroAoListarProdutos() {
        doThrow(RuntimeException.class)
                .when(repository)
                .findByKeyIdCliente(any());

        assertThrows(RuntimeException.class, () -> gateway.listarProdutos(id));
        verify(repository, times(1)).findByKeyIdCliente(any());
        verify(mapper, never()).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void deveListarProdutosComSucesso() {
        when(repository.findByKeyIdCliente(any()))
                .thenReturn(List.of(produtoEntityFixture));

        when(mapper.toDomain(any()))
                .thenReturn(produtoFixture);

        assertDoesNotThrow(() -> gateway.listarProdutos(id));
        verify(repository, times(1)).findByKeyIdCliente(any());
        verify(mapper, never()).toEntity(any());
        verify(mapper, times(1)).toDomain(any());
    }

    @Test
    void deveRetornarErroAoBuscarProdutoNaLista() {
        doThrow(RuntimeException.class)
                .when(repository)
                .findByKeyIdClienteAndKeyIdProduto(any(), any());

        assertThrows(RuntimeException.class, () -> gateway.buscarProduto(id, id));
        verify(repository, times(1)).findByKeyIdClienteAndKeyIdProduto(any(), any());
        verify(mapper, never()).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void deveRetornarOptionalEmpty() {
        when(repository.findByKeyIdClienteAndKeyIdProduto(any(), any()))
                .thenReturn(Optional.empty());

        assertDoesNotThrow(() -> gateway.buscarProduto(id, id));
        verify(repository, times(1)).findByKeyIdClienteAndKeyIdProduto(any(), any());
        verify(mapper, never()).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void deveRetornarProdutoComSucesso() {
        when(repository.findByKeyIdClienteAndKeyIdProduto(any(), any()))
                .thenReturn(Optional.of(produtoEntityFixture));

        when(mapper.toDomain(any())).thenReturn(produtoFixture);

        assertDoesNotThrow(() -> gateway.buscarProduto(id, id));
        verify(repository, times(1)).findByKeyIdClienteAndKeyIdProduto(any(), any());
        verify(mapper, never()).toEntity(any());
        verify(mapper, times(1)).toDomain(any());
    }
}