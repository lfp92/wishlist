package dev.petrauskas.wishlist.entrypoint;

import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wishlist/v1")
public interface BuscaProdutoListaController {

    @Operation(summary = "Busca um produto específico na wishlist", tags = "wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoDto.class))}),
            @ApiResponse(responseCode = "400", description = "ID do cliente ou ID do produto inválidos",
                    content = @Content)
    })
    @GetMapping("/clientes/{id_cliente}/produtos/{id_produto}")
    ResponseEntity<ProdutoDto> listarProdutos(@PathVariable("id_cliente") UUID idCliente,
                                              @PathVariable("id_produto") UUID idProduto);
}
