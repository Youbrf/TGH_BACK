package be.icc.tgh.service;

import be.icc.tgh.model.Administrateur;
import be.icc.tgh.repository.AdministrateurR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurS {
    @Autowired
    private AdministrateurR repo;

    public List<Administrateur> getAllAdministrateurs(){
        return repo.findAll();
    }
    public Administrateur getAdministrateurByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Administrateur creerAdministrateur(Administrateur admin){
        return repo.save(admin);
    }
    public Administrateur updateAdministrateur(Administrateur admin){
        return repo.save(admin);
    }
    public void deleteAdministrateur(Long id){
        repo.deleteById(id);
    }
}
