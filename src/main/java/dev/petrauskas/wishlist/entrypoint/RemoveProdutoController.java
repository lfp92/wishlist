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
public interface RemoveProdutoController {

    @Operation(summary = "Remove um produto da wishlist", tags = "wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "ID do cliente ou ID do produto inválidos",
                    content = @Content)
    })
    @DeleteMapping("/clientes/{id_cliente}/produtos/{id_produto}")
    ResponseEntity<ProdutoDto> removerProdutoLista(@PathVariable("id_cliente") UUID idCliente,
                                                   @PathVariable("id_produto") UUID idProduto);
}
