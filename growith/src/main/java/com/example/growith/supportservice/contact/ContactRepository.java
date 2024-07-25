package com.example.growith.supportservice.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Modifying
    @Query("UPDATE Contact c SET c.type.id = null where c.type.id = :typeId")
    void updateTypeIdToNull(@Param("typeId") int typeId);
}
