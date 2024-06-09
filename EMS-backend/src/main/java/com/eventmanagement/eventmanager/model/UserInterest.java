package com.eventmanagement.eventmanager.model;
import jakarta.persistence.*;

@Entity
@Table
public class UserInterest {
    @Id
    @SequenceGenerator(
            name="user_interest_sequence",
            sequenceName="user_interest_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "user_interest_sequence"
    )
    private long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "interestId")
    private Interest interest;

    public UserInterest() {
    }

    public UserInterest(long id, Person person, Interest interest) {
        this.id = id;
        this.person = person;
        this.interest = interest;
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

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "UserInterest{" +
                "id=" + id +
                ", user=" + person +
                ", interest=" + interest +
                '}';
    }
}
