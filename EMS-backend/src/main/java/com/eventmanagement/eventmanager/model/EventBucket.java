package com.eventmanagement.eventmanager.model;

import jakarta.persistence.*;

@Entity
@Table
public class EventBucket {
    @Id
    @SequenceGenerator(
            name="event bucket_sequence",
            sequenceName="event bucket_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "event bucket_sequence"
    )
    private long id;
    @OneToOne
    @JoinColumn(name = "personId",referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "eventId",referencedColumnName = "id")
    private Event event;

    public EventBucket() {
    }

    public EventBucket(long id, Person person, Event event) {
        this.id = id;
        this.person = person;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getUser() {
        return person;
    }

    public void setUser(Person person) {
        this.person = person;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventBucket{" +
                "id=" + id +
                ", user=" + person +
                ", event=" + event +
                '}';
    }
}
