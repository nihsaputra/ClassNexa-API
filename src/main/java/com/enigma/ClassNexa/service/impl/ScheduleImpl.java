package com.enigma.ClassNexa.service.impl;

import com.enigma.ClassNexa.entity.Schedule;
import com.enigma.ClassNexa.repository.ScheduleRepositoryRama;
import com.enigma.ClassNexa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleImpl implements ScheduleService {
    private final ScheduleRepositoryRama scheduleRepositoryRama;
    @Override
    public Schedule getByIdSchedule(String id) {
        Optional<Schedule> byId = scheduleRepositoryRama.findById(id);
        Schedule schedule = Schedule.builder()
                .id(byId.get().getId())
                .meeting_link(byId.get().getMeeting_link())
                .start_class(byId.get().getStart_class())
                .end_class(byId.get().getEnd_class())
//                .classes(byId.get().getClasses())
                .build();
        return schedule;

    }
}
