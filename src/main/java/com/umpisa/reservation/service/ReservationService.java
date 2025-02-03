package com.umpisa.reservation.service;

import org.springframework.stereotype.Service;
import com.umpisa.reservation.repository.ReservationRepository;
import com.umpisa.reservation.entity.Reservation;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Retrieves all reservations, regardless of status. Not used for now.
     * 
     * @return a list of all reservations
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Retrieves all active reservations, i.e. those not marked as 'cancelled'.
     * 
     * @return a list of all active reservations, ordered by reserve date and time
     *         in ascending order
     */
    public List<Reservation> getAllActiveReservations() {
        return reservationRepository.findByStatusNotOrderByReserveDateAscReserveTimeAsc("cancelled");
    }

    /**
     * Retrieves a reservation by its ID.
     * 
     * @param id the ID of the reservation to retrieve
     * @return an Optional containing the reservation if found, otherwise an empty
     *         Optional
     */
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    /**
     * Creates a new reservation.
     * 
     * @param reservation the reservation to create
     * @return the created reservation
     */
    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus("created");
        return reservationRepository.save(reservation);
    }

    /**
     * Updates an existing reservation by its ID.
     * 
     * @param id             the ID of the reservation to update
     * @param newReservation the reservation containing the updated values
     * @return an Optional containing the updated reservation if found, otherwise an
     *         empty Optional
     */
    public Optional<Reservation> updateReservation(Long id, Reservation newReservation) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setGuestNo(newReservation.getGuestNo());
            reservation.setReserveTime(newReservation.getReserveTime());
            return reservationRepository.save(reservation);
        });
    }

    /**
     * Cancels a reservation by setting its status to 'cancelled'.
     * 
     * @param id the ID of the reservation to cancel
     * @return an Optional containing the cancelled reservation if found, otherwise
     *         an
     *         empty Optional
     */
    public Optional<Reservation> cancelReservation(Long id) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setStatus("cancelled");
            return reservationRepository.save(reservation);
        });
    }

    /**
     * Deletes a reservation by its ID. Not used for now.
     * 
     * @param id the ID of the reservation to delete
     * @return true if the reservation was found and deleted, false if the
     *         reservation was not found
     */
    public boolean deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            return false;
        }
        reservationRepository.deleteById(id);
        return true;
    }

    /**
     * Updates a reservation by its ID to set its 'notified' field to true,
     * indicating
     * that the guest has been notified about the reservation.
     * 
     * @param id the ID of the reservation to update
     */
    public void updateReservationNotified(Long id) {
        reservationRepository.findById(id).map(reservation -> {
            reservation.setNotified(true);
            return reservationRepository.save(reservation);
        });
    }
}