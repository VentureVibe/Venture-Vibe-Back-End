package com.example.back_end.service;

import com.example.back_end.model.CommunityPost;
import com.example.back_end.model.PostReport;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.PostReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostReportService {

    @Autowired
    private PostReportRepository postReportRepository;

    @Transactional
    public PostReport addPostReport(PostReport postReport) {
        CommunityPost communityPost = postReport.getPost();

        if (communityPost == null) {
            throw new IllegalArgumentException("CommunityPost cannot be null");
        }

        Traveler traveler = postReport.getUserReported();

        if (traveler == null) {
            throw new IllegalArgumentException("Traveler cannot be null");
        }

        postReport.setPost(communityPost);
        postReport.setUserReported(traveler);

        return postReportRepository.save(postReport);
    }

    public List<PostReport> getAllPostReports() {
        return postReportRepository.findAll();
    }
}
