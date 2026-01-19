package com.example.TimeTracker.Repository;

import com.example.TimeTracker.Model.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalRepository extends JpaRepository<Interval, Long> {
}
