package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.PedidoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosUserRepository extends JpaRepository<PedidoUser, Long> {
}
