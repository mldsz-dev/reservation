package com.umpisa.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umpisa.reservation.entity.Reservation;
import com.umpisa.reservation.exception.ResourceNotFoundException;
import com.umpisa.reservation.responseModel.ApiResponse;
import com.umpisa.reservation.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Operation(summary = "Create a new reservation", description = "This API creates a new reservation.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created successfully")
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createReservation(@Valid @RequestBody Reservation reservation) {

        Reservation savedReservation = reservationService.createReservation(reservation);
        ApiResponse<Reservation> response = new ApiResponse<>("Create success", savedReservation,
                HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Fetches all active reservations", description = "ResponseEntity of ApiResponse containing all active reservations.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Fetch success")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Reservation>>> getAllActiveReservations() {
        List<Reservation> reservations = reservationService.getAllActiveReservations();
        ApiResponse<List<Reservation>> response = new ApiResponse<>("Fetch success",
                reservations, HttpStatus.OK.value(), reservations.size());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Fetches a reservation by ID", description = "ResponseEntity of ApiResponse containing the reservation.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Fetch success")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "if the reservation is not found")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getReservationById(@PathVariable Long id) {

        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with ID " + id
                        + " not found"));

        ApiResponse<Reservation> response = new ApiResponse<>("Data found",
                reservation, HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Updates a reservation based on the ID", description = "ResponseEntity containing the updated Reservation if successful, otherwise ResponseEntity with NOT_FOUND status and an ApiResponse indicating 'Update failed'.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Update success")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Reservation>> updateReservation(@PathVariable Long id,
            @RequestBody Reservation updatedReservation) {
        Optional<Reservation> updated = reservationService.updateReservation(id, updatedReservation);
        if (updated.isPresent()) {
            ApiResponse<Reservation> response = new ApiResponse<>("Update success", updated.get(),
                    HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Reservation> response = new ApiResponse<>("Update failed",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Operation(summary = "Cancels a reservation based on the provided ID", description = "ResponseEntity containing the updated Reservation if successful, otherwise ResponseEntity with NOT_FOUND status and an ApiResponse indicating 'Update failed'.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Update success")
    @PutMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse<Reservation>> cancelReservation(@PathVariable Long id) {
        Optional<Reservation> updated = reservationService.cancelReservation(id);
        if (updated.isPresent()) {
            String preferredContact = updated.get().getPreferredContact();
            ApiResponse<Reservation> response = new ApiResponse<>("Cancel success via " + preferredContact,
                    updated.get(),
                    HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Reservation> response = new ApiResponse<>("Update failed",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
