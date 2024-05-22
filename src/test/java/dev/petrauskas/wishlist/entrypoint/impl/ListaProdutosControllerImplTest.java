package dev.petrauskas.wishlist.entrypoint.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import dev.petrauskas.wishlist.entrypoint.mapper.ProdutoMapper;
import dev.petrauskas.wishlist.usecase.ListaProdutosUsecase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@WebMvcTest(ListaProdutosControllerImpl.class)
class ListaProdutosControllerImplTest {

    private static final String PATH = "/wishlist/v1/clientes/{id_cliente}/produtos";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListaProdutosUsecase usecase;

    @MockBean
    private ProdutoMapper mapper;

    @Test
    @SneakyThrows
    void deveRetornarBadRequestSeIdClienteForInvalido() {
        mockMvc.perform(get(PATH, "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get(PATH, 123))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get(PATH, false))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void deveListarProdutosComSucesso() {
        UUID id = UUID.randomUUID();

        when(usecase.listarProdutosCliente(any()))
                .thenReturn(List.of(Produto.builder().build()));

        ProdutoDto fixture = ProdutoDto.builder()
                .idProduto(id)
                .idCliente(id)
                .nome("Nome produto")
                .build();

        when(mapper.toDto(any())).thenReturn(fixture);

        mockMvc.perform(get(PATH, id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(List.of(fixture))));
    }
}