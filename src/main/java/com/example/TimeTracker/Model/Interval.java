package com.example.TimeTracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "intervals")
@NoArgsConstructor
@Data
public class Interval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_sec", nullable = false)
    private int start;

    @Column(name = "end_sec", nullable = false)
    private int end;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ActivityType type;

    public Interval(int start, int end, ActivityType type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }
}
