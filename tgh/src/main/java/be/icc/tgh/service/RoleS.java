package be.icc.tgh.service;

import be.icc.tgh.model.Role;
import be.icc.tgh.repository.RoleR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleS {
    @Autowired
    private RoleR repo;

    public List<Role> getAllRoles(){
        return repo.findAll();
    }
    public Role getRoleByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Role creerRole(Role role){
        return repo.save(role);
    }
    public Role updateRole(Role role){
        return repo.save(role);
    }
    public void deleteRole(Long id){
        repo.deleteById(id);
    }
}
