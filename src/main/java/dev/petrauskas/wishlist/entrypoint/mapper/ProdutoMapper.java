package dev.petrauskas.wishlist.entrypoint.mapper;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.entrypoint.json.ProdutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(source = "idCliente", target = "idCliente")
    @Mapping(source = "dto.idProduto", target = "idProduto")
    @Mapping(source = "dto.nome", target = "nome")
    Produto toDomain(ProdutoDto dto, UUID idCliente);

    ProdutoDto toDto(Produto domain);
}