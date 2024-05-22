package dev.petrauskas.wishlist.gateway.mapper;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.entities.ProdutoClienteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoEntityMapper {

    @Mapping(source = "entity.key.idCliente", target = "idCliente")
    @Mapping(source = "entity.key.idProduto", target = "idProduto")
    @Mapping(source = "entity.nomeProduto", target = "nome")
    Produto toDomain(ProdutoClienteEntity entity);

    @InheritInverseConfiguration
    ProdutoClienteEntity toEntity(Produto domain);

}
