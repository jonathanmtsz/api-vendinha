package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.User;
import com.api_vendinha.api.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciar operações relacionadas aos usuários.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users") // Define o caminho base para as requisições deste controlador.
public class UserController {

    // Injeção de dependência do serviço de usuários.
    private final UserServiceInterface userService;

    /**
     * Construtor para injeção de dependência do serviço de usuários.
     *
     * @param userService O serviço de usuários a ser injetado.
     */
    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Método para salvar um novo usuário.
     *
     * @param userRequestDto DTO que contém os dados do usuário a ser salvo.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado.
     */
    @PostMapping // Define que este método lida com requisições HTTP POST.
    public  ResponseEntity<?> salvar(@RequestBody UserRequestDto userRequestDto) {
        try{
            return ResponseEntity.ok(userService.save(userRequestDto));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro - " + e.getMessage());
        }


    }

    @PutMapping("/{id}")
    public UserResponseDto update(
            @PathVariable long id,
            @RequestBody UserRequestDto userRequestDto
    ){
        return userService.update(id, userRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto userById(
            @PathVariable long id
    ){
        return userService.getId(id);
    }

    @PutMapping("/{id}/status")
    public UserResponseDto setActive(
            @PathVariable long id
    ){
        return userService.setActive(id);
    }

    @GetMapping("/listAll")
    public List<User> listAll(){
        return userService.listAllUsers();
    }


}
