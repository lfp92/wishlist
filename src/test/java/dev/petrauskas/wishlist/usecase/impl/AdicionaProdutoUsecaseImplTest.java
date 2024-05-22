package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import dev.petrauskas.wishlist.usecase.error.LimiteDeItensAlcancadoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdicionaProdutoUsecaseImplTest {

    @InjectMocks
    private AdicionaProdutoUsecaseImpl usecase;

    @Mock
    private ProdutoGateway gateway;
    private UUID id;
    private Produto produtoFixture;

    @BeforeEach
    void setup() {
        id = UUID.randomUUID();

        produtoFixture = Produto.builder()
                .idProduto(id)
                .idCliente(id)
                .nome("Nome produto")
                .build();

    }

    @Test
    void deveRetornarErroAoVerificarTamanhoLista() {
        when(gateway.listarProdutos(any()))
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> usecase.adicionarProduto(produtoFixture));
        verify(gateway, times(1)).listarProdutos(any());
        verify(gateway, never()).adicionarProduto(any());
    }

    @Test
    void deveRetornarExceptionSeLimiteDaListaForAlcancado() {

        ReflectionTestUtils.setField(usecase, "TAMANHO_MAXIMO_LISTA", 1);

        when(gateway.listarProdutos(any()))
                .thenReturn(List.of(produtoFixture));

        assertThrows(LimiteDeItensAlcancadoException.class, () -> usecase.adicionarProduto(produtoFixture));
        verify(gateway, times(1)).listarProdutos(any());
        verify(gateway, never()).adicionarProduto(any());
    }

    @Test
    void deveAdicionarProdutoCorretamente() {

        ReflectionTestUtils.setField(usecase, "TAMANHO_MAXIMO_LISTA", 10);

        when(gateway.listarProdutos(any())).thenReturn(List.of(produtoFixture));

        assertDoesNotThrow(() -> usecase.adicionarProduto(produtoFixture));
        verify(gateway, times(1)).listarProdutos(any());
        verify(gateway, times(1)).adicionarProduto(any());
    }
}