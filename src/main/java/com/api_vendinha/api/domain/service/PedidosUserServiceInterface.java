package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.PedidoRequestDTO;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDTO;
import com.api_vendinha.api.domain.entities.PedidoUser;
import com.api_vendinha.api.domain.entities.Produto;

public interface PedidosUserServiceInterface {
    int checkQnt(Produto produto);
    PedidoResponseDTO formatPedidoRequestDTO(PedidoUser pedido);
}
