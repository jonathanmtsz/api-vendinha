package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "produto")
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias da entidade pelo JPA.
@AllArgsConstructor

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor do ID será gerado automaticamente pelo banco de dados (auto-incremento).
    private Long produto_id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int qntd;
    @Column(nullable = false)
    private String preco;

    @OneToMany(mappedBy = "produto_id")
    Set<PedidoUser> produtos;

    @OneToMany(mappedBy = )
}
