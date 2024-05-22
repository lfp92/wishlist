package dev.petrauskas.wishlist.entrypoint;

import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wishlist/v1")
public interface AdicionaProdutoController {

    @Operation(summary = "Adiciona um produto na wishlist", tags = "wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoDto.class))}),
            @ApiResponse(responseCode = "400", description = "ID do cliente ou corpo da requisição inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Limite de itens na wishlist alcançado",
                    content = @Content)
    })
    @PostMapping("/clientes/{id_cliente}/produtos")
    ResponseEntity<ProdutoDto> adicionarProduto(@PathVariable("id_cliente") UUID idCliente,
                                                @Valid @RequestBody ProdutoDto produtoDto);
}