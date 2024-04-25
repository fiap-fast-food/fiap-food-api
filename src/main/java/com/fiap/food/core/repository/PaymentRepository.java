package com.fiap.food.core.repository;

import com.fiap.food.core.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    @Query("SELECT p FROM PaymentEntity p JOIN p.order o WHERE o.confirmationCode = :confirmationCode")
    Optional<PaymentEntity> findByOrderConfirmationCode(@Param("confirmationCode") String confirmationCode);

}
