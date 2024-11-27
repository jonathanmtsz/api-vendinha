package com.api_vendinha.api.domain.dtos.response;

import lombok.Data;

@Data
public class PedidoResponseDTO {
    public String id;
    public Long user_id;
    public Long product_id;
    public int quantity;
    public float price;
}
