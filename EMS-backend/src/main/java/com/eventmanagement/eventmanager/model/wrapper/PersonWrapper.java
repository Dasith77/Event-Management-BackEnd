package com.eventmanagement.eventmanager.model.wrapper;

import com.eventmanagement.eventmanager.model.Interest;
import com.eventmanagement.eventmanager.model.Person;

import java.util.List;

public class PersonWrapper {
    private Person person;
    private List<Interest> interestList;

    public PersonWrapper() {
    }

    public PersonWrapper(Person person, List<Interest> interestList) {
        this.person = person;
        this.interestList = interestList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }
}
