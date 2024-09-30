package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.PedidosRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDTO;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDTO;
import com.api_vendinha.api.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProdutoServiceImpl implements ProdutoServiceInterface {

    private final PedidosRepository pedidosRepository;

    @Autowired
    public ProdutoServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }


    @Override
    public ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        produto.setQntd(produtoRequestDTO.getQntd());
        produto.setPreco(produtoRequestDTO.getPreco());
        produto.setName(produtoRequestDTO.getName());

        Produto savedProduto = pedidosRepository.save(produto);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(savedProduto.ge());
        produtoResponseDTO.setQntd(savedProduto.getQntd());
        produtoResponseDTO.setName(savedProduto.getName());
        produtoResponseDTO.setPreco(savedProduto.getPreco());

        return produtoResponseDTO;
    }

    @Override
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto foundProduto = pedidosRepository.findById(id).orElseThrow();

        foundProduto.setName(produtoRequestDTO.getName());
        foundProduto.setQntd(produtoRequestDTO.getQntd());
        foundProduto.setPreco(produtoRequestDTO.getPreco());

        Produto savedProduto = pedidosRepository.save(foundProduto);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(savedProduto.getPedido_id());
        produtoResponseDTO.setQntd(savedProduto.getQntd());
        produtoResponseDTO.setName(savedProduto.getName());
        produtoResponseDTO.setPreco(savedProduto.getPreco());

        return produtoResponseDTO;

    }

    @Override
    public ProdutoResponseDTO getId(Long id) {
        Produto savedProduto =  pedidosRepository.findById(id).orElseThrow();

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(savedProduto.getPedido_id());
        produtoResponseDTO.setQntd(savedProduto.getQntd());
        produtoResponseDTO.setName(savedProduto.getName());
        produtoResponseDTO.setPreco(savedProduto.getPreco());

        return produtoResponseDTO;
    }

    @Override
    public ProdutoResponseDTO delete(Long id) {
        Produto deleted = pedidosRepository.findById(id).orElseThrow();

        pedidosRepository.delete(deleted);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(deleted.getPedido_id());
        produtoResponseDTO.setQntd(deleted.getQntd());
        produtoResponseDTO.setName(deleted.getName());
        produtoResponseDTO.setPreco(deleted.getPreco());

        return produtoResponseDTO;
    }

    @Override
    public Set<Produto> findAllByPedido_idIsNotEmpty() {
        return pedidosRepository.findAllByPedido_idIsNotEmpty();
    }


}
