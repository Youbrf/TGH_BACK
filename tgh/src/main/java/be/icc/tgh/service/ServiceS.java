package be.icc.tgh.service;

import be.icc.tgh.model.Service;
import be.icc.tgh.repository.ServiceR;
import org.hibernate.type.internal.UserTypeVersionJavaTypeWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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


}
