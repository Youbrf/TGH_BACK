package be.icc.tgh.controller;


import be.icc.tgh.model.Service;
import be.icc.tgh.service.ServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Services")
public class ServiceC {
    @Autowired
    private ServiceS service;

    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> Services = service.getAllServices();
        return new ResponseEntity<>(Services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable("id") Long id) {
        Service Service = service.getServiceByID(id);
        if (Service == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Service, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service serv) {
        Service createdService = service.creerService(serv);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable("id") Long id, @RequestBody Service serv) {
        serv.setId(id);
        Service updatedService = service.updateService(serv);
        if (updatedService == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable("id") Long id) {
        service.deleteService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/categorie/{id}")
    public List<Service> getServicesByCategorie(@PathVariable Long id) {
        return service.getServicesByCategorie(id);
    }
}
