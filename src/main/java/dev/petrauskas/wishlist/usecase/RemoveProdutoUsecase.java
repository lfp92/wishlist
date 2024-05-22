package dev.petrauskas.wishlist.usecase;

import java.util.UUID;

public interface RemoveProdutoUsecase {

    void removerProduto(UUID idCliente, UUID idProduto);
}
