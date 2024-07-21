package com.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDTO {
    private Long id;
    private String user1;
    private String user2;
    private String startTime;
    private Boolean readByUser1;
    private Boolean readByUser2;
    private String lastMessage;
}
