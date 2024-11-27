package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.PedidoUser;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PedidosUserRepository extends JpaRepository<PedidoUser, Long> {
    @Query("SELECT u FROM PedidoUser u WHERE u.status = TRUE")
    List<PedidoUser> findAllActive();

}
