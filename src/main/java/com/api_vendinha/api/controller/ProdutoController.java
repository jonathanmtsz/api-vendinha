package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDTO;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDTO;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.service.ProdutoServiceInterface;
import com.api_vendinha.api.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoServiceInterface produtoService;

    @Autowired
    public ProdutoController(ProdutoServiceInterface produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping // Define que este método lida com requisições HTTP POST.
    public ProdutoResponseDTO salvar(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtoService.save(produtoRequestDTO);
    }


    @GetMapping("/com-ids-existentes")
    public ResponseEntity<Set<Produto>> getAllByIdExists() {
        Set<Produto> produtos = produtoService.findAllByPedido_idIsNotEmpty();
        return ResponseEntity.ok(produtos);
    }
}
