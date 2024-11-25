package com.example.back_end.controller;

import com.example.back_end.dto.PostReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.back_end.model.PostReport;
import com.example.back_end.service.PostReportService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class PostReportController {

    @Autowired
    private PostReportService postReportService;

    @PostMapping
    public PostReport addPostReport(@RequestBody PostReport postReport) {
        return  postReportService.addPostReport(postReport);
    }

    @GetMapping
    public List<PostReport> getAllPostReports() {
        return postReportService.getAllPostReports();
    }

//    @PutMapping("/{id}")
//    public PostReport updatePostReport(@RequestBody PostReport postReport, @PathVariable Long id) {
//        return postReportService.updatePostReportById(postReport, id);
//    }

    // Update the status of a post report (Keep or Reject)
    @PutMapping("/{id}")
    public PostReport updatePostReport(@RequestBody PostReportDTO postReportDTO, @PathVariable Long id) {
        return postReportService.updatePostReportById(postReportDTO, id);
    }
}
