package com.example.back_end.repository;

import com.example.back_end.model.EmailSubscriber;
import org.reactivestreams.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.Flow;

public interface SubscriberRepository extends JpaRepository<EmailSubscriber,Long> {
    Optional<EmailSubscriber> findByEmail(String email);
}
