package be.icc.tgh.service;

import be.icc.tgh.model.Service;
import be.icc.tgh.repository.ServiceR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Service
public class ServiceS {
    @Autowired
    private ServiceR repo;
    @Autowired
    private CategorieServiceS catServ;

    public List<Service> getAllServices(){
        return repo.findAll();
    }
    public Service getServiceByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Service creerService(Service service){
        return repo.save(service);
    }
    public Service updateService(Service service){
        return repo.save(service);
    }
    public void deleteService(Long id){
        repo.deleteById(id);
    }

    public List<Service> getServicesByCategorie(Long id) {
        return repo.findByCategorieId(id);
    }


    public Service getRandomService() {
        List<Service> services = repo.findAll();
        Random random = new Random();
        long randomIndex = random.nextLong(services.size());
        return repo.findById(randomIndex).orElse(null);
    }

}
