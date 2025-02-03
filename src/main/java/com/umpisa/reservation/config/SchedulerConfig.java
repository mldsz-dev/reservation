package com.umpisa.reservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.umpisa.reservation.service.SchedulerService;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    SchedulerService schedulerService;

    /**
     * Runs every 10 seconds to check for upcoming reservations.
     * This is a fixed rate schedule, so even if the previous execution
     * hasn't finished, a new one will be started.
     */
    @Scheduled(fixedRate = 10000)
    public void notifyUsers() {
        schedulerService.checkReservations();
    }
}