package com.eventmanagement.eventmanager.model.dto;

import java.sql.Time;
import java.time.LocalDate;

public class EventDTO {
    private Long id;
    private String name;
    private String venue;
    private LocalDate startDate;
    private LocalDate endDate;
    private Time startTime;
    private Time endTime;
    private String description;
    private String ticketDetails;
    private String musicians;
    private Boolean availability;
    private Long userId;

    // Default constructor
    public EventDTO() {
    }

    // Parameterized constructor
    public EventDTO(Long id, String name, String venue, LocalDate startDate, LocalDate endDate, Time startTime, Time endTime, String description, String ticketDetails, String musicians, Boolean availability, Long userId) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.ticketDetails = ticketDetails;
        this.musicians = musicians;
        this.availability = availability;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicketDetails() {
        return ticketDetails;
    }

    public void setTicketDetails(String ticketDetails) {
        this.ticketDetails = ticketDetails;
    }

    public String getMusicians() {
        return musicians;
    }

    public void setMusicians(String musicians) {
        this.musicians = musicians;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", venue='" + venue + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", ticketDetails='" + ticketDetails + '\'' +
                ", musicians='" + musicians + '\'' +
                ", availability=" + availability +
                ", userId=" + userId +
                '}';
    }
}