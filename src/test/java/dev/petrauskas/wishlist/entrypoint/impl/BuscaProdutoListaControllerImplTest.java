package dev.petrauskas.wishlist.entrypoint.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import dev.petrauskas.wishlist.entrypoint.mapper.ProdutoMapper;
import dev.petrauskas.wishlist.usecase.BuscaProdutoListaUsecase;
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

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@WebMvcTest(BuscaProdutoListaControllerImpl.class)
class BuscaProdutoListaControllerImplTest {

    private static final String PATH = "/wishlist/v1/clientes/{id_cliente}/produtos/{id_produto}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuscaProdutoListaUsecase usecase;

    @MockBean
    private ProdutoMapper mapper;

    @Test
    @SneakyThrows
    void deveRetornarErroIdClienteInvalido() {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get(PATH, "abc", id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get(PATH, 123, id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get(PATH, false, id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void deveRetornarErroIdProdutoInvalido() {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get(PATH, id, "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get(PATH, id, 123))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get(PATH, id, false))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    @SneakyThrows
    void deveBuscarProdutoComSucesso() {
        UUID id = UUID.randomUUID();

        when(usecase.buscarProdutoLista(any(), any()))
                .thenReturn(Optional.of(Produto.builder().build()));

        ProdutoDto fixture = ProdutoDto.builder()
                .idProduto(id)
                .idCliente(id)
                .nome("Nome produto")
                .build();

        when(mapper.toDto(any())).thenReturn(fixture);

        mockMvc.perform(get(PATH, id, id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(fixture)));
    }
}