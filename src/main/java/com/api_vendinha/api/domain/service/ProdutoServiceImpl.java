package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDTO;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDTO;
import com.api_vendinha.api.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProdutoServiceImpl implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository pedidosRepository) {
        this.produtoRepository = pedidosRepository;
    }


    @Override
    public ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        produto.setQntd(produtoRequestDTO.getQntd());
        produto.setPreco(produtoRequestDTO.getPreco());
        produto.setName(produtoRequestDTO.getName());

        Produto savedProduto = produtoRepository.save(produto);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(savedProduto.getProduto_id());
        produtoResponseDTO.setQntd(savedProduto.getQntd());
        produtoResponseDTO.setName(savedProduto.getName());
        produtoResponseDTO.setPreco(savedProduto.getPreco());

        return produtoResponseDTO;
    }

    @Override
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto foundProduto = produtoRepository.findById(id).orElseThrow();

        foundProduto.setName(produtoRequestDTO.getName());
        foundProduto.setQntd(produtoRequestDTO.getQntd());
        foundProduto.setPreco(produtoRequestDTO.getPreco());

        Produto savedProduto = produtoRepository.save(foundProduto);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(savedProduto.getProduto_id());
        produtoResponseDTO.setQntd(savedProduto.getQntd());
        produtoResponseDTO.setName(savedProduto.getName());
        produtoResponseDTO.setPreco(savedProduto.getPreco());

        return produtoResponseDTO;

    }

    @Override
    public ProdutoResponseDTO getId(Long id) {
        Produto savedProduto =  produtoRepository.findById(id).orElseThrow();

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(savedProduto.getProduto_id());
        produtoResponseDTO.setQntd(savedProduto.getQntd());
        produtoResponseDTO.setName(savedProduto.getName());
        produtoResponseDTO.setPreco(savedProduto.getPreco());

        return produtoResponseDTO;
    }

    @Override
    public ProdutoResponseDTO delete(Long id) {
        Produto deleted = produtoRepository.findById(id).orElseThrow();

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(deleted.getProduto_id());
        produtoResponseDTO.setQntd(deleted.getQntd());
        produtoResponseDTO.setName(deleted.getName());
        produtoResponseDTO.setPreco(deleted.getPreco());

        return produtoResponseDTO;
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }


}
