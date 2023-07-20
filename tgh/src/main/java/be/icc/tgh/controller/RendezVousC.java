package be.icc.tgh.controller;

import be.icc.tgh.model.RendezVous;
import be.icc.tgh.model.User;
import be.icc.tgh.service.RendezVousS;
import be.icc.tgh.service.UserS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/RendezVous")
public class RendezVousC {
    @Autowired
    private RendezVousS service;
    @Autowired
    private UserS userS;

    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> RendezVous = service.getAllRendezVous();
        return new ResponseEntity<>(RendezVous, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable("id") Long id) {
        RendezVous RendezVous = service.getRendezVousByID(id);
        if (RendezVous == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(RendezVous, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RendezVous> createRendezVous(@RequestBody RendezVous rendezVous) {
        RendezVous createdRendezVous = service.creerRendezVous(rendezVous);
        return new ResponseEntity<>(createdRendezVous, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable("id") Long id, @RequestBody RendezVous rendezVous) {
        rendezVous.setId(id);
        RendezVous updatedRendezVous = service.updateRendezVous(rendezVous);
        if (updatedRendezVous == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRendezVous, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable("id") Long id) {
        service.deleteRendezVous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{id}")
    public List<RendezVous> searchRendezVous(@PathVariable("id") Integer id,@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.findReservationByEmployer(id,date);
    }

    @GetMapping("/user")
    public List<RendezVous> getReservationByUser(@RequestParam("id") Integer id){
        User user = this.userS.getUserByID(id);
        if (user.getRole().toString() == "USER"){
            return service.findByUser(user);
        }else {
            return service.findByEmployer(user);
        }
    }

}
