package dev.petrauskas.wishlist.entrypoint.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoDto {

    @JsonProperty("id_cliente")
    private UUID idCliente;

    @NotNull(message = "\"id_produto\" é obrigatório")
    @JsonProperty("id_produto")
    private UUID idProduto;

    @NotBlank(message = "\"nome_produto\" não pode estar vazio")
    @JsonProperty("nome_produto")
    private String nome;
}
