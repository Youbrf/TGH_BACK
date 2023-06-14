package be.icc.tgh.controller;

import be.icc.tgh.model.CategorieService;
import be.icc.tgh.service.CategorieServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CategorieServices")
@CrossOrigin(origins = "*")
public class CategorieServiceC {
    @Autowired
    private CategorieServiceS service;

    @GetMapping
    public ResponseEntity<List<CategorieService>> getAllCategorieServices() {
        List<CategorieService> CategorieServices = service.getAllCategorieServices();
        return new ResponseEntity<>(CategorieServices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieService> getCategorieServiceById(@PathVariable("id") Long id) {
        CategorieService CategorieService = service.getCategorieServiceByID(id);
        if (CategorieService == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(CategorieService, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategorieService> createCategorieService(@RequestBody CategorieService categorieService) {
        CategorieService createdCategorieService = service.creerCategorieService(categorieService);
        return new ResponseEntity<>(createdCategorieService, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieService> updateCategorieService(@PathVariable("id") Long id, @RequestBody CategorieService categorieService) {
        categorieService.setId(id);
        CategorieService updatedCategorieService = service.updateCategorieService(categorieService);
        if (updatedCategorieService == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCategorieService, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorieService(@PathVariable("id") Long id) {
        service.deleteCategorieService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
