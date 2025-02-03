package com.umpisa.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umpisa.reservation.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Finds all entities with a status not equal to the specified value,
     * ordered by reserve date and time in ascending order.
     * 
     * @param status the status to exclude
     * @return a list of entities matching the criteria
     */
    List<Reservation> findByStatusNotOrderByReserveDateAscReserveTimeAsc(String status);

    /**
     * Finds all entities with a reserve date equal to the specified date and a
     * reserve time
     * within the specified start and end times, and a status not equal to
     * 'cancelled' and
     * notified set to false.
     * 
     * @param date      the reserve date to match
     * @param startTime the start time (inclusive) of the time range to match
     * @param endTime   the end time (exclusive) of the time range to match
     * @return a list of entities matching the criteria
     */
    @Query("SELECT r FROM Reservation r WHERE r.reserveDate = :date AND r.reserveTime BETWEEN :startTime AND :endTime AND r.status != 'cancelled' AND r.notified = false")
    List<Reservation> findAllByReserveDateAndTimeBetween(@Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

}