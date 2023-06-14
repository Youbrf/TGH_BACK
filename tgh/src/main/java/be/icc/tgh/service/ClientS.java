package be.icc.tgh.service;

import be.icc.tgh.model.Client;
import be.icc.tgh.repository.ClientR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientS {
    @Autowired
    private ClientR repo;

    public List<Client> getAllClients(){
        return repo.findAll();
    }
    public Client getClientByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Client creerClient(Client client){
        return repo.save(client);
    }
    public Client updateClient(Client client){
        return repo.save(client);
    }
    public void deleteClient(Long id){
        repo.deleteById(id);
    }
}
