package com.api_vendinha.api.domain.service;


import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDTO;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDTO;
import com.api_vendinha.api.domain.entities.Produto;

import java.util.List;
import java.util.Set;

public interface ProdutoServiceInterface {
    ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO);
    ProdutoResponseDTO update(Long id, ProdutoRequestDTO produtoRequestDTO);
    ProdutoResponseDTO getId(Long id);
    ProdutoResponseDTO delete(Long id);
    List<Produto> findAllByPedido_idIsNotEmpty();
}
