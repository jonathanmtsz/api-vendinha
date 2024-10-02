package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;

@Data
public class PedidoRequestDTO {
    private Long user_id;
    private Long product_id;
    private int quantity;
}
