package com.fiap.food.core.repository;

import com.fiap.food.core.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByConfirmationCode(String confirmationCode);

    @Query("SELECT o FROM OrderEntity o " +
            "WHERE o.status IN ('READY', 'IN_PREPARATION', 'RECEIVED') AND o.status <> 'FINISHED' " +
            "ORDER BY " +
            "  CASE WHEN o.status = 'READY' THEN 1 " +
            "       WHEN o.status = 'IN_PREPARATION' THEN 2 " +
            "       WHEN o.status = 'RECEIVED' THEN 3 END ASC, " +
            "  o.dateTimeOrder ASC, " +
            "  CASE WHEN o.customer IS NULL THEN 1 ELSE 0 END")
    List<OrderEntity> findOrdersOrderedByStatusAndDateTimeOrder();
}
