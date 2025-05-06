package com.example.schedule_manager.service;

import com.example.schedule_manager.model.Activity;
import com.example.schedule_manager.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public Activity getById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }

    public void delete(Long id) {
        activityRepository.deleteById(id);
    }
}

