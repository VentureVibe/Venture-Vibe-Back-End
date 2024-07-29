package com.example.back_end.repository;


import com.example.back_end.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findByUser1Id(String userId);

    Optional<Conversation> findByUser1IdAndUser2Id(String user1Id, String user2Id);

    List<Conversation> findByUser2Id(String userId);
}
