package com.eventmanagement.eventmanager.model;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class EventCategory {
    @Id
    @SequenceGenerator(
            name="event category_sequence",
            sequenceName="event category_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "event category_sequence"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "eventId",referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private Category category;
    private Integer score;

    public EventCategory() {
    }

    public EventCategory(long id, Event event, Category category, Integer score) {
        this.id = id;
        this.event = event;
        this.category = category;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
