package dev.petrauskas.wishlist.entrypoint.impl;

import dev.petrauskas.wishlist.usecase.RemoveProdutoUsecase;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@WebMvcTest(RemoveProdutoControllerImpl.class)
class RemoveProdutoControllerImplTest {

    private static final String PATH = "/wishlist/v1/clientes/{id_cliente}/produtos/{id_produto}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RemoveProdutoUsecase usecase;

    @Test
    @SneakyThrows
    void deveRetornarErroIdClienteInvalido() {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete(PATH, "abc", id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete(PATH, 123, id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete(PATH, false, id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void deveRetornarErroIdProdutoInvalido() {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete(PATH, id, "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete(PATH, id, 123))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete(PATH, id, false))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void deveRemoverProdutoComSucesso() {
        UUID id = UUID.randomUUID();

        doNothing().when(usecase).removerProduto(any(), any());

        mockMvc.perform(delete(PATH, id, id))
                .andExpect(status().isNoContent());
    }

}