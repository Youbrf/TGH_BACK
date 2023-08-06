package be.icc.tgh.controller;

import be.icc.tgh.model.Reservation;
import be.icc.tgh.model.User;
import be.icc.tgh.service.ReservationS;
import be.icc.tgh.service.UserS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/Reservation")
public class ReservationC {
    @Autowired
    private ReservationS service;
    @Autowired
    private UserS userS;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservation() {
        List<Reservation> Reservation = service.getAllReservation();
        return new ResponseEntity<>(Reservation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Reservation Reservation = service.getReservationByID(id);
        if (Reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Reservation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = service.creerReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        Reservation updatedReservation = service.updateReservation(reservation);
        if (updatedReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        service.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{id}")
    public List<Reservation> searchReservation(@PathVariable("id") Integer id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.findReservationByEmployer(id,date);
    }

    @GetMapping("/user")
    public List<Reservation> getReservationByUser(@RequestParam("id") Integer id){
        User user = this.userS.getUserByID(id);
        if (user.getRole().toString() == "USER"){
            return service.findByUser(user);
        }else {
            return service.findByEmployer(user);
        }
    }

}