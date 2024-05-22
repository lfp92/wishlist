package dev.petrauskas.wishlist.gateway.repository;

import dev.petrauskas.wishlist.gateway.entities.ProdutoClienteEntity;
import dev.petrauskas.wishlist.gateway.entities.ProdutoClienteEntityKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoClienteRepository extends CassandraRepository<ProdutoClienteEntity, ProdutoClienteEntityKey> {

    List<ProdutoClienteEntity> findByKeyIdCliente(UUID idCliente);

    Optional<ProdutoClienteEntity> findByKeyIdClienteAndKeyIdProduto(UUID idCliente, UUID idProduto);

    void deleteByKeyIdClienteAndKeyIdProduto(UUID idCliente, UUID idProduto);
}
