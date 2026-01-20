package com.example.TimeTracker.Service;

import com.example.TimeTracker.DTO.IntervalDTO;
import com.example.TimeTracker.Model.Interval;
import com.example.TimeTracker.Repository.IntervalRepository;
import com.example.TimeTracker.exception.OverlapException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalService {

    private final IntervalRepository repository;

    public IntervalService(IntervalRepository repository) {
        this.repository = repository;
    }

    public List<Interval> getAll() {
        return repository.findAllByOrderByStartAsc();
    }

    public Interval add(@Valid IntervalDTO dto) throws Exception {
        if (dto.getEnd() <= dto.getStart()) {
            throw new IllegalArgumentException("Начало должно быть меньше конца");
        }

        Interval entity = new Interval(dto.getStart(), dto.getEnd(), dto.getType());
        try {
            return repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("intervals_int4range_excl")) {
                throw new OverlapException("Пересечение с существующим интервалом");
            }
            throw new IllegalArgumentException("Ошибка валидации данных: " + e.getMessage());
        }
    }
}