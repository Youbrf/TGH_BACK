package be.icc.tgh.service;

import be.icc.tgh.model.Administrateur;
import be.icc.tgh.model.CategorieService;
import be.icc.tgh.repository.CategorieServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceS {
    @Autowired
    private CategorieServiceR repo;

    public List<CategorieService> getAllCategorieServices(){
        return  repo.findAll();
    }
    public CategorieService getCategorieServiceByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public CategorieService creerCategorieService(CategorieService categorieService){
        return repo.save(categorieService);
    }
    public CategorieService updateCategorieService(CategorieService categorieService){
        return repo.save(categorieService);
    }
    public void deleteCategorieService(Long id){
        repo.deleteById(id);
    }
}
