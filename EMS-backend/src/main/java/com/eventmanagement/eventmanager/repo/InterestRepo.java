package com.eventmanagement.eventmanager.repo;

import com.eventmanagement.eventmanager.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterestRepo extends JpaRepository<Interest,Long> {

    void deleteInterestById(Long id);

    @Query("SELECT c FROM Interest c WHERE c.id = :id")
    Interest findInterestById(@Param("id") Long id);
}
