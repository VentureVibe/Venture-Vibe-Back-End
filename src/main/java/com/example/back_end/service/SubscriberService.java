package com.example.back_end.service;
import com.example.back_end.model.EmailSubscriber;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.SubscriberRepository;
import com.example.back_end.repository.TravelerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private TravelerRepo travelerRepo;


    public void saveSubscriber(String email) {

        EmailSubscriber subscriber = new EmailSubscriber();
        subscriber.setEmail(email);
        subscriberRepository.save(subscriber);
    }
}

