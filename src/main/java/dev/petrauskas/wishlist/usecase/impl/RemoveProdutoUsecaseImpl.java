package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import dev.petrauskas.wishlist.usecase.RemoveProdutoUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RemoveProdutoUsecaseImpl implements RemoveProdutoUsecase {

    private final ProdutoGateway gateway;

    @Override
    public void removerProduto(UUID idCliente, UUID idProduto) {
        gateway.removerProduto(idCliente, idProduto);
    }
}
