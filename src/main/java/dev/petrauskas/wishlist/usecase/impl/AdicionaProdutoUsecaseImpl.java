package dev.petrauskas.wishlist.usecase.impl;

import dev.petrauskas.wishlist.domain.Produto;
import dev.petrauskas.wishlist.gateway.ProdutoGateway;
import dev.petrauskas.wishlist.usecase.AdicionaProdutoUsecase;
import dev.petrauskas.wishlist.usecase.error.LimiteDeItensAlcancadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdicionaProdutoUsecaseImpl implements AdicionaProdutoUsecase {

    private final ProdutoGateway gateway;

    @Value("${params.wishlist-limit}")
    private Integer TAMANHO_MAXIMO_LISTA;

    @Override
    public void adicionarProduto(Produto produto) {
        List<Produto> listaProdutos = gateway.listarProdutos(produto.getIdCliente());

        if (listaProdutos.size() >= TAMANHO_MAXIMO_LISTA) {
            throw new LimiteDeItensAlcancadoException("O limite de " + TAMANHO_MAXIMO_LISTA + " itens foi alcançado. Não é possível adicionar mais itens.");
        }

        gateway.adicionarProduto(produto);
    }
}
