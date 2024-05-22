package dev.petrauskas.wishlist.entrypoint.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.entrypoint.BuscaProdutoListaController;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import dev.petrauskas.wishlist.entrypoint.mapper.ProdutoMapper;
import dev.petrauskas.wishlist.usecase.BuscaProdutoListaUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuscaProdutoListaControllerImpl implements BuscaProdutoListaController {

    private final BuscaProdutoListaUsecase usecase;
    private final ProdutoMapper mapper;

    @Override
    public ResponseEntity<ProdutoDto> listarProdutos(UUID idCliente, UUID idProduto) {

        Produto produto = usecase.buscarProdutoLista(idCliente, idProduto).orElse(null);

        return ResponseEntity.ok(mapper.toDto(produto));
    }
}
