package dev.petrauskas.wishlist.entrypoint.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import dev.petrauskas.wishlist.entrypoint.mapper.ProdutoMapper;
import dev.petrauskas.wishlist.usecase.AdicionaProdutoUsecase;
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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@WebMvcTest(AdicionaProdutoControllerImpl.class)
class AdicionaProdutoControllerImplTest {
    private static final String PATH = "/wishlist/v1/clientes/{id_cliente}/produtos";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdicionaProdutoUsecase usecase;

    @MockBean
    private ProdutoMapper mapper;

    @Test
    @SneakyThrows
    void deveRetornarBadRequestSeIdClienteForInvalido() {
        UUID id = UUID.randomUUID();

        String requestBody = new ObjectMapper()
                .writeValueAsString(ProdutoDto.builder()
                        .idProduto(id)
                        .nome("Nome produto")
                        .build());

        mockMvc.perform(post(PATH, "abc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(post(PATH, 123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(post(PATH, false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void deveRetornarInternalServerErrorSeRequestBodyForInvalido() {
        UUID id = UUID.randomUUID();

        String requestBody = new ObjectMapper()
                .writeValueAsString(ProdutoDto.builder()
                        .build());

        mockMvc.perform(post(PATH, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void deveAdicionarProdutoComSucesso() {
        UUID id = UUID.randomUUID();

        doNothing().when(usecase).adicionarProduto(any());

        String requestBody = new ObjectMapper()
                .writeValueAsString(ProdutoDto.builder()
                        .idProduto(id)
                        .nome("Nome produto")
                        .build());

        mockMvc.perform(post(PATH, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}