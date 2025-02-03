package com.umpisa.reservation.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    private String guestName;

    @NotNull(message = "Phone number is required")
    @Size(min = 11, max = 11, message = "Invalid format")
    private String phoneNumber;

    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull(message = "Date is required")
    private LocalDate reserveDate;

    @NotNull(message = "Time is required")
    private LocalTime reserveTime;

    @NotNull(message = "Guest count is required")
    @Min(value = 1, message = "Guest count must be at least 1")
    @Max(value = 5, message = "Guest count must be less than or equal to 5")
    private int guestNo;

    private String preferredContact;
    private LocalDateTime createdDatetime;
    private String status;

    @Column(nullable = false)
    private boolean notified;;

    public Reservation() {
    }

    public Reservation(String status, String guestName, String phoneNumber, String email, LocalDate reserveDate,
            LocalTime reserveTime, int guestNo, String preferredContact, LocalDateTime createdDatetime, boolean notified) {
        this.status = status;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
        this.guestNo = guestNo;
        this.preferredContact = preferredContact;
        this.createdDatetime = createdDatetime;
        this.notified = notified;
    }

    @PrePersist
    protected void onCreate() {
        this.createdDatetime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public LocalTime getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(LocalTime reserveTime) {
        this.reserveTime = reserveTime;
    }

    public int getGuestNo() {
        return guestNo;
    }

    public void setGuestNo(int guestNo) {
        this.guestNo = guestNo;
    }

    public String getPreferredContact() {
        return preferredContact;
    }

    public void setPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

}
