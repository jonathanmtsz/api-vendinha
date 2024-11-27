package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.response.PedidoResponseDTO;
import com.api_vendinha.api.domain.entities.PedidoUser;
import com.api_vendinha.api.domain.entities.Produto;

import java.util.List;

public interface PedidosUserServiceInterface {
    int checkQnt(Produto produto);
    PedidoResponseDTO formatPedidoRequestDTO(PedidoUser pedido);
    List<PedidoUser> listAll ();
    PedidoResponseDTO inativarPedido(long IdPedidoUser);
    List<PedidoUser> listAllActive();
}
