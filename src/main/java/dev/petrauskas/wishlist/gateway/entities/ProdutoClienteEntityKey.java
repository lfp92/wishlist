package dev.petrauskas.wishlist.gateway.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@Data
@Builder
@PrimaryKeyClass
public class ProdutoClienteEntityKey {

    @PrimaryKeyColumn(name = "id_cliente", type = PrimaryKeyType.PARTITIONED)
    private final UUID idCliente;

    @PrimaryKeyColumn(name = "id_produto", type = PrimaryKeyType.CLUSTERED)
    private final UUID idProduto;

}
