package com.example.back_end.model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

   @OneToOne
   @JoinColumn(name = "traveler_id",referencedColumnName = "id")
   private Traveler traveler;


}