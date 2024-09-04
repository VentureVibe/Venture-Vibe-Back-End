package com.example.back_end.controller;


import com.example.back_end.model.PostReport;
import com.example.back_end.model.TravelBudget;
import com.example.back_end.service.PostReportService;
import com.example.back_end.service.TravelBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/travelbudget")
public class TravelBudgetController {

    @Autowired
    private TravelBudgetService travelBudgetService;

    @PostMapping("/{travelPlanId}")
    public TravelBudget addTravelBudget(
            @PathVariable("travelPlanId") Long travelPlanId,
            @RequestBody TravelBudget travelBudget) {

        // Set the travelPlanId to the travelBudget if needed


        return travelBudgetService.addTravelBudget(travelBudget,travelPlanId);
    }


    @PutMapping("/{travelPlanId}")
    public TravelBudget updateTravelBudget(
            @PathVariable("travelPlanId") Long travelPlanId,
            @RequestBody TravelBudget travelBudget) {

        // Set the travelPlanId to the travelBudget if needed


        return travelBudgetService.updateTravelBudget(travelBudget,travelPlanId);
    }

    @GetMapping("/{travelPlanId}")
    public List<TravelBudget> getTravelBudgetsByTravelPlanId(
            @PathVariable("travelPlanId") Long travelPlanId
          ) {

        return travelBudgetService.getTravelBudgetsByTravelPlanId(travelPlanId);
    }

    @DeleteMapping("/{travelPlanId}")
    public void addTravelBudget(
            @PathVariable("travelPlanId") Long travelPlanId
       ) {

       travelBudgetService.deleteTravelBudget(travelPlanId);
    }

}
