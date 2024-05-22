package dev.petrauskas.wishlist.entrypoint;

import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wishlist/v1")
public interface ListaProdutosController {

    @Operation(summary = "Retorna todos os produtos da wishlist do cliente", tags = "wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens encontrados",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProdutoDto.class)))}),
            @ApiResponse(responseCode = "400", description = "ID do cliente inválido",
                    content = @Content)
    })
    @GetMapping("/clientes/{id_cliente}/produtos")
    ResponseEntity<List<ProdutoDto>> listarProdutos(@PathVariable("id_cliente") UUID idCliente);
}
