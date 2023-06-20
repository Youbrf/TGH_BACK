package be.icc.tgh.controller;

import be.icc.tgh.model.RendezVous;
import be.icc.tgh.model.Service;
import be.icc.tgh.service.RendezVousS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/RendezVous")
@CrossOrigin(origins = "*")
public class RendezVousC {
    @Autowired
    private RendezVousS service;

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

    @GetMapping("/search")
    public List<RendezVous> searchRendezVous(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        System.out.println(date);
        return service.findByDateReservation(date);
    }
}
