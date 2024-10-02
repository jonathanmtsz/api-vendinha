package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDTO;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDTO;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.service.ProdutoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAllByIdExists() {
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}/delete")
    public ProdutoResponseDTO delete(@PathVariable long id) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtoService.delete(id);
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO getById(@PathVariable long id) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtoService.getId(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO salvar(
            @RequestBody ProdutoRequestDTO produtoRequestDTO,
            @PathVariable long id
    ) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtoService.update(id, produtoRequestDTO);
    }

}
