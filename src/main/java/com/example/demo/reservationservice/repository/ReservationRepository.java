package com.example.demo.reservationservice.repository;

import com.example.demo.reservationservice.dto.TicketDTO;
import com.example.demo.reservationservice.entitiy.Reservation;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r.sSpot FROM Reservation r WHERE r.fId = :fId")
    List<String> findReservationSSpotByFId(@Param("fId") Long fId);

//    TicketDTO findByRId(Long rId);
}
