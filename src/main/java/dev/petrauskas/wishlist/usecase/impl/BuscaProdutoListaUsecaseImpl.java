package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import dev.petrauskas.wishlist.usecase.BuscaProdutoListaUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscaProdutoListaUsecaseImpl implements BuscaProdutoListaUsecase {

    private final ProdutoGateway gateway;

    @Override
    public Optional<Produto> buscarProdutoLista(UUID idCliente, UUID idProduto) {
        return gateway.buscarProduto(idCliente, idProduto);
    }
}
