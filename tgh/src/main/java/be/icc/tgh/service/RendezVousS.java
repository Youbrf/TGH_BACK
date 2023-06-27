package be.icc.tgh.service;

import be.icc.tgh.model.RendezVous;
import be.icc.tgh.repository.RendezVousR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RendezVousS {
    @Autowired
    private RendezVousR repo;

    public List<RendezVous> getAllRendezVous(){
        return repo.findAll();
    }
    public RendezVous getRendezVousByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public RendezVous creerRendezVous(RendezVous rendezvous){return repo.save(rendezvous);}
    public RendezVous updateRendezVous(RendezVous rendezvous){
        return repo.save(rendezvous);
    }
    public void deleteRendezVous(Long id){
        repo.deleteById(id);
    }

    public List<RendezVous> findByDateReservation(LocalDate date) {
        return  repo.findByDateReservation(date);
    }
}
