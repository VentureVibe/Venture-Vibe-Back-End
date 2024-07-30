package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommunityPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Column(length = 20000 , nullable = false)
    private String content;

    @Column(length = 2048)
    private String imgUrl;

    @Column(name = "user_id")
    private String userId;

    private Integer totalLikes = 0;
    private Integer totalComments = 0;
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Traveler traveler;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> postLikes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postComments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostReport> postReports = new ArrayList<>();

    // Method to add a PostLike
    public void addPostLike(PostLike postLike) {
        postLikes.add(postLike);
        postLike.setPost(this);
    }

    // Method to add a PostComments
    public void addPostComment(PostComment postComment) {
        postComments.add(postComment);
        postComment.setPost(this);
    }

    // Method to remove a PostLike
    public void removePostLike(PostLike postLike) {
        postLikes.remove(postLike);
        postLike.setPost(null);
    }
}
