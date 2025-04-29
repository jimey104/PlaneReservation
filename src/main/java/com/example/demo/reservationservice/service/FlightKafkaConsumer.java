package com.example.demo.reservationservice.service;

import com.example.demo.reservationservice.dto.FlightDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FlightKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final ReservationService reservationService;

    public FlightKafkaConsumer(ReservationService reservationService) {
        this.reservationService = reservationService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "flight-topic", groupId = "flight-consumer-group")
    public void listen(String message) {
        try{
            FlightDto dto = objectMapper.readValue(message, FlightDto.class);
            System.out.println("받은 항공편: " + dto);

            reservationService.save(dto);
        }catch (JsonProcessingException e){
            System.out.println("❌ 메시지 파싱 실패" + message);
            e.printStackTrace();
        }

    }
}

