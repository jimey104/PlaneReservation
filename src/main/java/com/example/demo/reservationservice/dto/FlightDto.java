package com.example.demo.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    private Long id;

    private String departureName;

    private String arrivalName;

    private String departureTime;

    private String arrivalTime;

    private String aircraftType;

    private String flightClass;

    private int seatCount;

    private int price;

}