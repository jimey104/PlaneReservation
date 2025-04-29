package com.example.demo.reservationservice.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Access(AccessType.FIELD)
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long rId;

    @Column(name = "u_id")
    private Long uId;

    @Column(name = "f_id")
    private Long fId;

    @Column(name = "r_date")
    private LocalDateTime rDate;

    @Column(name = "f_departure")
    private String fDeparture;

    @Column(name = "f_arrival")
    private String fArrival;

    @Column(name = "f_departure_time")
    private LocalDateTime fDepartureTime;

    @Column(name = "f_arrival_time")
    private LocalDateTime fArrivalTime;

    @Column(name = "c_id")
    private Long cId;

    @Column(name = "fAircraftType")
    private String fAircraftType;

    @Column(name = "u_name")
    private String uName;

    @Column(name = "s_name")
    private String sName;

    @Column(name = "s_spot")
    private String sSpot;

    @Column(name = "s_class")
    private String sClass;
}
