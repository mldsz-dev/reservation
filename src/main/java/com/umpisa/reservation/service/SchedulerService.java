package com.umpisa.reservation.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umpisa.reservation.entity.Reservation;
import com.umpisa.reservation.repository.ReservationRepository;

@Service
public class SchedulerService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    ReservationService reservationService;

    /**
     * Checks for upcoming reservations and sends a notification to the guests.
     * Runs every 10 seconds.
     * The reservations are considered upcoming if the reserve date is today and
     * the reserve time is within the next 4 hours.
     * The notification is sent via the guest's preferred contact method
     * (email or SMS).
     * The reservation is then marked as notified so that it won't be notified
     * again.
     */
    public void checkReservations() {
        System.out.println("SCHEDULER: checkReservations " + LocalDate.now() + "-" + LocalTime.now());

        List<Reservation> upcomingReservations = reservationRepository
                .findAllByReserveDateAndTimeBetween(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(4));

        System.out.println("SCHEDULER: reservations found count - " + upcomingReservations.size());

        for (Reservation reservation : upcomingReservations) {
            notificationService.sendNotification(reservation);
            reservationService.updateReservationNotified(reservation.getId());
            System.out.println("SCHEDULER: notified reservation id " + reservation.getId());
        }
    }
}
