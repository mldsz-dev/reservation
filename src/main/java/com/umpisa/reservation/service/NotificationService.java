package com.umpisa.reservation.service;

import org.springframework.stereotype.Service;

import com.umpisa.reservation.entity.Reservation;

@Service
public class NotificationService {

    /**
     * Sends a notification to the guest about their upcoming reservation.
     * Currently, this is just a placeholder that prints to the console.
     * 
     * @param reservation the reservation to notify the guest about
     */
    public void sendNotification(Reservation reservation) {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("Reminder: " + reservation.getGuestName());
        msgBuilder.append(reservation.getReserveDate() + " - " + reservation.getReserveTime() + " for "
                + reservation.getGuestNo());
        String prefferedContact = reservation.getPreferredContact();
        System.out.println("Sending notification via " + prefferedContact + ": " + msgBuilder);

    }
}
