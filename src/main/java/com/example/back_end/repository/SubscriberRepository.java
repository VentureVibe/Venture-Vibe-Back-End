package com.example.back_end.repository;

import com.example.back_end.model.EmailSubscriber;
import org.reactivestreams.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.Flow;

public interface SubscriberRepository extends JpaRepository<EmailSubscriber,Long> {
}
