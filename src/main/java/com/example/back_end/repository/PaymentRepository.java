package com.example.back_end.repository;

import com.example.back_end.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByCategory(String category);
    List<Payment> findByReceiver(String receiver);
}
