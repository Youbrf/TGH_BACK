package be.icc.tgh.service;

import be.icc.tgh.model.User;
import be.icc.tgh.repository.UserR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserS {
    @Autowired
    private UserR repo;

    public List<User> getAllClients(){
        return repo.findAll();
    }
    public User getClientByID(Integer id){
        return repo.findById(id).orElse(null);
    }
    public User creerClient(User user){
        return repo.save(user);
    }
    public User updateClient(User user){
        return repo.save(user);
    }
    public void deleteClient(Integer id){
        repo.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
