package com.eventmanagement.eventmanager.model;
import jakarta.persistence.*;

@Entity
@Table
public class Interest {
    @Id
    @SequenceGenerator(
            name="interest_sequence",
            sequenceName="interest_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "interest_sequence"
    )
    private long id;
    private String interestKey;
    private String interestName;

    public Interest() {
    }

    public Interest(long id, String interestKey, String interestName) {
        this.id = id;
        this.interestKey = interestKey;
        this.interestName = interestName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInterestKey() {
        return interestKey;
    }

    public void setInterestKey(String interestKey) {
        this.interestKey = interestKey;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", interestKey='" + interestKey + '\'' +
                ", interestName='" + interestName + '\'' +
                '}';
    }
}
