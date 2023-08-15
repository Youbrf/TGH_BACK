package be.icc.tgh.controller;

import be.icc.tgh.model.Reservation;
import be.icc.tgh.model.Role;
import be.icc.tgh.model.StripeResponse;
import be.icc.tgh.model.User;
import be.icc.tgh.service.ReservationS;
import be.icc.tgh.service.UserS;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Reservation> reservation = service.getAllReservation();
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Reservation reservation = service.getReservationByID(id);
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = service.creerReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PutMapping("updated/{id}")
    public ResponseEntity<Reservation> updateReservationById(@PathVariable("id") Long id) {
        Reservation reservation = service.getReservationByID(id);
        reservation.setEtatPaiement("PAID");
        reservation.setStatutReservation("CONFIRMED");
        reservation.setModePaiement("EN LIGNE");
        Reservation updatedReservation = service.updateReservation(reservation);
        if (updatedReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
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
    public ResponseEntity<List<Reservation>> getReservationByUser(@RequestParam("id") Integer id){
        List<Reservation> reservation = new ArrayList<Reservation>();
        User user = userS.getUserByID(id);
        if (user.getRole().equals(Role.USER)){
            reservation = service.findByUser(user);
        }else {
            reservation = service.findByEmployer(user);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody Reservation reservation) throws StripeException {
        Session session = service.createSession(reservation);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.CREATED);
    }
}
