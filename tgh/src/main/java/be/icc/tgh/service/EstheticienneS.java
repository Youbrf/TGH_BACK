package be.icc.tgh.service;

import be.icc.tgh.model.Estheticienne;
import be.icc.tgh.repository.EstheticienneR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstheticienneS {
    @Autowired
    private EstheticienneR repo;

    public List<Estheticienne> getAllEstheticiennes(){
        return repo.findAll();
    }
    public Estheticienne getEstheticienneByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Estheticienne creerEstheticienne(Estheticienne estheticienne){
        return repo.save(estheticienne);
    }
    public Estheticienne updateEstheticienne(Estheticienne estheticienne){
        return repo.save(estheticienne);
    }
    public void deleteEstheticienne(Long id){
        repo.deleteById(id);
    }
}
