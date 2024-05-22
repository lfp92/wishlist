package dev.petrauskas.wishlist.usecase;

import dev.petrauskas.wishlist.domain.Produto;

import java.util.Optional;
import java.util.UUID;

public interface BuscaProdutoListaUsecase {

    Optional<Produto> buscarProdutoLista(UUID idCliente, UUID idProduto);
}
