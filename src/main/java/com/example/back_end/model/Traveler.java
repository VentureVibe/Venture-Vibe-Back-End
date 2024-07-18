package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Traveler {

    @Id
    private String id;

    @Column(length = 320,nullable = false)
    private String email;

    @Column(length = 255)
    private String name;

    @Column(length = 255,nullable = false)
    private String password;

    @Column(length = 2048)
    private String profileImg;

    @Column(length = 2048)
    private String coverImg;

    @OneToMany(mappedBy = "traveler")
    private Set<CommunityPost> comminutyPosts;

    @JsonIgnore
    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostLike> postLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostLike> postComments;

}
