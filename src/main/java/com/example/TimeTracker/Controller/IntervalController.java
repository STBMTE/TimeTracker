package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.IntervalDTO;
import com.example.TimeTracker.Model.Interval;
import com.example.TimeTracker.Service.IntervalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intervals")
public class IntervalController {

    private final IntervalService service;

    public IntervalController(IntervalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Interval> getAll() {
        var a = service.getAll();
        return ResponseEntity.ok(service.getAll()).getBody();
    }

    @PostMapping
    public ResponseEntity<Interval> add(@RequestBody @Valid IntervalDTO request) throws Exception {
        return ResponseEntity.ok(service.add(request));
    }
}