package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import dev.petrauskas.wishlist.usecase.ListaProdutosUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListaProdutosUsecaseImpl implements ListaProdutosUsecase {

    private final ProdutoGateway gateway;

    @Override
    public List<Produto> listarProdutosCliente(UUID idCliente) {
        return gateway.listarProdutos(idCliente);
    }
}
