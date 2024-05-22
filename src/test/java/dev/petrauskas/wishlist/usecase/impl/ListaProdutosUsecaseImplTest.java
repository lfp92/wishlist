package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListaProdutosUsecaseImplTest {

    @InjectMocks
    private ListaProdutosUsecaseImpl usecase;

    @Mock
    private ProdutoGateway gateway;

    @Test
    void deveRetornarErroAoChamarGateway() {
        when(gateway.listarProdutos(any()))
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> usecase.listarProdutosCliente(UUID.randomUUID()));
        verify(gateway, times(1)).listarProdutos(any());
    }

    @Test
    void deveRetornarListaVazia() {
        when(gateway.listarProdutos(any()))
                .thenReturn(List.of());

        List<Produto> produtos = assertDoesNotThrow(() -> usecase.listarProdutosCliente(UUID.randomUUID()));
        assertEquals(0, produtos.size());
        verify(gateway, times(1)).listarProdutos(any());
    }

    @Test
    void deveRetornarListaPreenchida() {
        when(gateway.listarProdutos(any()))
                .thenReturn(List.of(Produto.builder().build()));

        List<Produto> produtos = assertDoesNotThrow(() -> usecase.listarProdutosCliente(UUID.randomUUID()));
        assertEquals(1, produtos.size());
        verify(gateway, times(1)).listarProdutos(any());
    }
}