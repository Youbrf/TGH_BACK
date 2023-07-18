package be.icc.tgh.service;

import be.icc.tgh.model.Role;
import be.icc.tgh.model.User;
import be.icc.tgh.repository.UserR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserS {
    @Autowired
    private UserR repo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers(){
        return repo.findAll();
    }
    public User getUserByID(Integer id){
        return repo.findById(id).orElse(null);
    }
    public User creerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }
    public User updateUser(User user){
        return repo.save(user);
    }
    public void deleteUser(Integer id){
        repo.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User getRandomUser(Role role) {
        List<User> users = repo.findByRole(role);
        if (users.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(users.size());
        User randomUser = users.get(randomIndex);

        return repo.findById(randomUser.getId()).orElse(null);
    }
}
