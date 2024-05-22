package dev.petrauskas.wishlist.gateway.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import dev.petrauskas.wishlist.gateway.mapper.ProdutoEntityMapper;
import dev.petrauskas.wishlist.gateway.repository.ProdutoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoClienteRepository repository;

    private final ProdutoEntityMapper mapper;

    @Override
    public void adicionarProduto(Produto produto) {
        repository.save(mapper.toEntity(produto));
    }

    @Override
    public void removerProduto(UUID idCliente, UUID idProduto) {
        repository.deleteByKeyIdClienteAndKeyIdProduto(idCliente, idProduto);
    }

    @Override
    public List<Produto> listarProdutos(UUID idCliente) {
        return repository.findByKeyIdCliente(idCliente)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscarProduto(UUID idCliente, UUID idProduto) {
        return repository.findByKeyIdClienteAndKeyIdProduto(idCliente, idProduto)
                .map(mapper::toDomain);
    }
}
