package com.api_vendinha.api.domain.dtos.response;

import lombok.Data;

@Data
public class ProdutoResponseDTO {
    private long id;
    private String name;
    private int qntd;
    private float preco;

}
