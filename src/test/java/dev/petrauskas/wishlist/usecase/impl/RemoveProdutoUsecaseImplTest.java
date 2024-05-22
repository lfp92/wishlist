package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveProdutoUsecaseImplTest {

    @InjectMocks
    private RemoveProdutoUsecaseImpl usecase;

    @Mock
    private ProdutoGateway gateway;

    @Test
    void deveRetornarErroAoChamarGateway() {
        doThrow(RuntimeException.class).when(gateway).removerProduto(any(), any());

        assertThrows(RuntimeException.class,
                () -> usecase.removerProduto(UUID.randomUUID(), UUID.randomUUID()));

        verify(gateway, times(1)).removerProduto(any(), any());
    }

    @Test
    void deveRemoverComSucesso() {
        doNothing().when(gateway).removerProduto(any(), any());

        assertDoesNotThrow(() -> usecase.removerProduto(UUID.randomUUID(), UUID.randomUUID()));

        verify(gateway, times(1)).removerProduto(any(), any());
    }
}