package dev.petrauskas.wishlist.usecase;

import dev.petrauskas.wishlist.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface ListaProdutosUsecase {

    List<Produto> listarProdutosCliente(UUID idCliente);
}
