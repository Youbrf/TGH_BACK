package be.icc.tgh.controller;

import be.icc.tgh.model.Estheticienne;
import be.icc.tgh.service.EstheticienneS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Estheticiennes")
public class EstheticienneC {
    @Autowired
    private EstheticienneS service;

    @GetMapping
    public ResponseEntity<List<Estheticienne>> getAllEstheticiennes() {
        List<Estheticienne> Estheticiennes = service.getAllEstheticiennes();
        return new ResponseEntity<>(Estheticiennes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estheticienne> getEstheticienneById(@PathVariable("id") Long id) {
        Estheticienne Estheticienne = service.getEstheticienneByID(id);
        if (Estheticienne == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Estheticienne, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estheticienne> createEstheticienne(@RequestBody Estheticienne estheticienne) {
        Estheticienne createdEstheticienne = service.creerEstheticienne(estheticienne);
        return new ResponseEntity<>(createdEstheticienne, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estheticienne> updateEstheticienne(@PathVariable("id") Long id, @RequestBody Estheticienne estheticienne) {
        estheticienne.setId(id);
        Estheticienne updatedEstheticienne = service.updateEstheticienne(estheticienne);
        if (updatedEstheticienne == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEstheticienne, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstheticienne(@PathVariable("id") Long id) {
        service.deleteEstheticienne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
