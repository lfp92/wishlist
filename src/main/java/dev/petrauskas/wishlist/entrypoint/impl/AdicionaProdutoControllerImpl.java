package dev.petrauskas.wishlist.entrypoint.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.entrypoint.AdicionaProdutoController;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import dev.petrauskas.wishlist.entrypoint.mapper.ProdutoMapper;
import dev.petrauskas.wishlist.usecase.AdicionaProdutoUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdicionaProdutoControllerImpl implements AdicionaProdutoController {

    private final AdicionaProdutoUsecase usecase;
    private final ProdutoMapper mapper;

    @Override
    public ResponseEntity<ProdutoDto> adicionarProduto(UUID idCliente, ProdutoDto produtoDto) {

        Produto produto = mapper.toDomain(produtoDto, idCliente);
        usecase.adicionarProduto(produto);

        return ResponseEntity.ok(mapper.toDto(produto));
    }
}
