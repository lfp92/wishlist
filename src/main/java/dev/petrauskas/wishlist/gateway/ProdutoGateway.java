package dev.petrauskas.wishlist.gateway;

import dev.petrauskas.wishlist.domain.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoGateway {

    void adicionarProduto(Produto produto);

    void removerProduto(UUID idCliente, UUID idProduto);

    List<Produto> listarProdutos(UUID idCliente);

    Optional<Produto> buscarProduto(UUID idCliente, UUID idProduto);


}
