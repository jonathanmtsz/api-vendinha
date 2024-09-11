package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;

@Data
public class ProdutoRequestDTO {
    private String name;
    private int qntd;
    private String preco;

}
