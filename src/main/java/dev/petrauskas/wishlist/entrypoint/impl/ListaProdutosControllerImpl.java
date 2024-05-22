package dev.petrauskas.wishlist.entrypoint.impl;

import dev.petrauskas.wishlist.entrypoint.ListaProdutosController;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import dev.petrauskas.wishlist.entrypoint.mapper.ProdutoMapper;
import dev.petrauskas.wishlist.usecase.ListaProdutosUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListaProdutosControllerImpl implements ListaProdutosController {

    private final ListaProdutosUsecase usecase;
    private final ProdutoMapper mapper;

    @Override
    public ResponseEntity<List<ProdutoDto>> listarProdutos(UUID idCliente) {

        return ResponseEntity.ok(usecase.listarProdutosCliente(idCliente)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
    }
}
