package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscaProdutoListaUsecaseImplTest {

    @InjectMocks
    private BuscaProdutoListaUsecaseImpl usecase;

    @Mock
    private ProdutoGateway gateway;

    @Test
    void deveRetornarErroAoChamarGateway() {
        when(gateway.buscarProduto(any(), any()))
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class,
                () -> usecase.buscarProdutoLista(UUID.randomUUID(), UUID.randomUUID()));

        verify(gateway, times(1)).buscarProduto(any(), any());
    }

    @Test
    void deveRetornarOptionalEmpty() {
        when(gateway.buscarProduto(any(), any()))
                .thenReturn(Optional.empty());

        Optional<Produto> produto = assertDoesNotThrow(() -> usecase.buscarProdutoLista(UUID.randomUUID(), UUID.randomUUID()));
        assertTrue(produto.isEmpty());
        verify(gateway, times(1)).buscarProduto(any(), any());
    }

    @Test
    void deveRetornarListaPreenchida() {
        when(gateway.buscarProduto(any(), any()))
                .thenReturn(Optional.of(Produto.builder().build()));

        Optional<Produto> produto = assertDoesNotThrow(() -> usecase.buscarProdutoLista(UUID.randomUUID(), UUID.randomUUID()));
        assertFalse(produto.isEmpty());
        verify(gateway, times(1)).buscarProduto(any(), any());
    }
}