package com.example.demo.reservationservice.controller;


import com.example.demo.reservationservice.dto.ReservationDTO;
import com.example.demo.reservationservice.dto.ReservationRequestDTO;
import com.example.demo.reservationservice.dto.SeatDTO;
import com.example.demo.reservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ReservationDTO reservationDTO) {
        String key = reservationService.saveInitialData(reservationDTO);
        return ResponseEntity.ok(key);
    }

    // 좌석 데이터 받기
    @GetMapping("/search")
    public ResponseEntity<ReservationRequestDTO> search(@RequestParam String key) {
        ReservationRequestDTO dto = reservationService.getReservationData(key);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    // 좌석 선택
    @PostMapping("/seats")
    public ResponseEntity<ReservationRequestDTO> passport(@RequestBody List<SeatDTO> seats, @RequestParam String key) {
        ReservationRequestDTO dto = reservationService.updateSeats(key, seats);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    // 좌석 lock
    @PostMapping("/seats/lock")
    public ResponseEntity<?> selectSeats(@RequestBody List<SeatDTO> seats, @RequestParam String key) {
        Map<String, List<String>> result = reservationService.tryLockSeats(key, seats);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    /**
     * fId(항공편 ID)에 해당하는 모든 락된 좌석 조회
     * 예: GET /api/reservation/seats/locked/all?fId=123
     */
    @GetMapping("/seats/lock")
    public ResponseEntity<List<String>> getLockedByFlight(@RequestParam String key) {
        List<String> locked = reservationService.getLockedSeatsByFlightId(key);
        return ResponseEntity.ok(locked);
    }

    // 예매 확정 좌석 잠금
    @GetMapping("/seats/status")
    public ResponseEntity<List<String>> reservedSeats(@RequestParam Long fId) {
        return ResponseEntity.ok(reservationService.getReservedSpots(fId));
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmReservation(@RequestParam String key) {
        boolean success = reservationService.confirmReservation(key);
        return success
                ? ResponseEntity.ok("예약이 완료되었습니다.")
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelReservation(@RequestParam String key) {
        boolean success = reservationService.cancelReservation(key);
        return success
                ? ResponseEntity.ok("예약을 취소했습니다.")
                : ResponseEntity.notFound().build();
    }
}

//
//private final ReservationService reservationService;
//
//@PostMapping("/")
//public ResponseEntity<String> create(@RequestParam Long uId,
//                                     @RequestParam String uName,
//                                     ) {
//
//    FlightDto flightDto = reservationService.getFlightDto();
//    String key = reservationService.saveInitialData(flightDto, uId, uName);
//    return ResponseEntity.ok(key);
//}
//
//// 좌석 데이터 받기
//@GetMapping("/search")
//public ResponseEntity<ReservationRequestDTO> search(@RequestParam String key) {
//    ReservationRequestDTO dto = reservationService.getReservationData(key);
//    return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
//}
//
//// 좌석 선택
//@PostMapping("/seats")
//public ResponseEntity<ReservationRequestDTO> passport(@RequestBody List<SeatDTO> seats, @RequestParam String key) {
//    ReservationRequestDTO dto = reservationService.updateSeats(key, seats);
//    return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
//}
//
//// 좌석 lock
//@PostMapping("/seats/lock")
//public ResponseEntity<?> selectSeats(@RequestBody List<SeatDTO> seats, @RequestParam String key) {
//    Map<String, List<String>> result = reservationService.tryLockSeats(key, seats);
//    return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
//}
//
///**
// * fId(항공편 ID)에 해당하는 모든 락된 좌석 조회
// * 예: GET /api/reservation/seats/locked/all?fId=123
// */
//@GetMapping("/seats/lock")
//public ResponseEntity<List<String>> getLockedByFlight(@RequestParam String key) {
//    List<String> locked = reservationService.getLockedSeatsByFlightId(key);
//    return ResponseEntity.ok(locked);
//}
//
//// 예매 확정 좌석 잠금
//@GetMapping("/seats/status")
//public ResponseEntity<List<String>> reservedSeats(@RequestParam Long fId) {
//    return ResponseEntity.ok(reservationService.getReservedSpots(fId));
//}
//
//@PostMapping("/confirm")
//public ResponseEntity<String> confirmReservation(@RequestParam String key) {
//    boolean success = reservationService.confirmReservation(key);
//    return success
//            ? ResponseEntity.ok("예약이 완료되었습니다.")
//            : ResponseEntity.notFound().build();
//}
//
//@PostMapping("/cancel")
//public ResponseEntity<String> cancelReservation(@RequestParam String key) {
//    boolean success = reservationService.cancelReservation(key);
//    return success
//            ? ResponseEntity.ok("예약을 취소했습니다.")
//            : ResponseEntity.notFound().build();
//}
