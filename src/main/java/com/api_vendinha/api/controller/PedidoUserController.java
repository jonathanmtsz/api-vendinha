package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.PedidoRequestDTO;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDTO;
import com.api_vendinha.api.domain.service.PedidosUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoUserController {
    private final PedidosUserImpl pedidosUserImpl;

    @Autowired
    public PedidoUserController(PedidosUserImpl pedidosUserImpl){
        this.pedidosUserImpl = pedidosUserImpl;

    }

    @PostMapping("/new")
    public PedidoResponseDTO save(@RequestBody PedidoRequestDTO pedidoRequestDTO){
        return pedidosUserImpl.save(pedidoRequestDTO);
    }
}
