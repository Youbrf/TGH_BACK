package be.icc.tgh.controller;

import be.icc.tgh.model.Administrateur;
import be.icc.tgh.service.AdministrateurS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Administrateurs")
public class AdministrateurC {
    @Autowired
    private AdministrateurS service;

    @GetMapping
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        List<Administrateur> Administrateurs = service.getAllAdministrateurs();
        return new ResponseEntity<>(Administrateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable("id") Long id) {
        Administrateur Administrateur = service.getAdministrateurByID(id);
        if (Administrateur == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Administrateur, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
        Administrateur createdAdministrateur = service.creerAdministrateur(administrateur);
        return new ResponseEntity<>(createdAdministrateur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable("id") Long id, @RequestBody Administrateur administrateur) {
        administrateur.setId(id);
        Administrateur updatedAdministrateur = service.updateAdministrateur(administrateur);
        if (updatedAdministrateur == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedAdministrateur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable("id") Long id) {
        service.deleteAdministrateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
