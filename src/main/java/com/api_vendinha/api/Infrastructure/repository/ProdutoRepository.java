package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repositório para a entidade User.
 *
 * Esta interface estende JpaRepository, o que fornece uma série de métodos prontos para realizar
 * operações de persistência no banco de dados para a entidade User.
 */
@Repository
public interface PedidosRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByName(String name);

    @Query("SELECT p FROM Produto p WHERE p.produto_id IS NOT NULL")
    Set<Produto> findAllByPedido_idIsNotEmpty();
}