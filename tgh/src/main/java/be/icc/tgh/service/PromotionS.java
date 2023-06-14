package be.icc.tgh.service;

import be.icc.tgh.model.Promotion;
import be.icc.tgh.repository.PromotionR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromotionS {
    @Autowired
    private PromotionR repo;

    public List<Promotion> getAllPromotions(){
        return repo.findAll();
    }
    public Promotion getPromotionByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Promotion creerPromotion(Promotion promotion){
        return repo.save(promotion);
    }
    public Promotion updatePromotion(Promotion promotion){
        return repo.save(promotion);
    }
    public void deletePromotion(Long id){
        repo.deleteById(id);
    }
}
