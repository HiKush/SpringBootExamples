package org.kush.quartz.controller;

import org.kush.quartz.vo.PriceChange;
import org.kush.quartz.service.PriceChangeService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
 public class PriceChangeController {
 @Autowired
 PriceChangeService priceChangeService;
 @PostMapping("/priceChange")// batch Posted by some other service???
 public ResponseEntity<String> pushPriceChangeObject( @RequestBody List<PriceChange> lstPriceChange) {
  try {
   String jobName = "testName";
   String jobGroup = "testJobGroup";
   String status = priceChangeService.triggerJob(jobName, jobGroup, lstPriceChange);
   if (status.contains("success")) {
    return ResponseEntity.ok("Job triggered successfully");
   } else {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to trigger job: ");
   }
  } catch (SchedulerException e) {
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to trigger job: " + e.getMessage());
  }
 }
}