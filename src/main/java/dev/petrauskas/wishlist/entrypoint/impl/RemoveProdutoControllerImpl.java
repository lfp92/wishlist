package dev.petrauskas.wishlist.entrypoint.impl;

import dev.petrauskas.wishlist.entrypoint.RemoveProdutoController;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import dev.petrauskas.wishlist.usecase.RemoveProdutoUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RemoveProdutoControllerImpl implements RemoveProdutoController {

    private final RemoveProdutoUsecase usecase;

    @Override
    public ResponseEntity<ProdutoDto> removerProdutoLista(UUID idCliente, UUID idProduto) {

        usecase.removerProduto(idCliente, idProduto);

        return ResponseEntity.noContent().build();
    }
}
