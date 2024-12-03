package com.example.back_end.service;
import com.example.back_end.model.EmailSubscriber;
import com.example.back_end.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public void saveSubscriber(String email) {
        EmailSubscriber subscriber = new EmailSubscriber();
        subscriber.setEmail(email);
        subscriberRepository.save(subscriber);
    }
}

