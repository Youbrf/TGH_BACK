package be.icc.tgh.service;

import be.icc.tgh.model.Reservation;
import be.icc.tgh.model.User;
import be.icc.tgh.repository.ReservationR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationS {
    @Autowired
    private ReservationR repo;

    public List<Reservation> getAllReservation(){
        return repo.findAll();
    }
    public Reservation getReservationByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Reservation creerReservation(Reservation Reservation){
        Reservation.setDateCreation(LocalDate.now());
        return repo.save(Reservation);
    }
    public Reservation updateReservation(Reservation Reservation){
        Reservation.setDateModification(LocalDate.now());
        return repo.save(Reservation);
    }
    public void deleteReservation(Long id){
        repo.deleteById(id);
    }

    public List<Reservation> findReservationByEmployer(Integer id, LocalDate date) {
        return  repo.findReservationByEmployer(id,date);
    }

    public List<Reservation> findByUser(User user) {
        return repo.findByUser(user);
    }

    public List<Reservation> findByEmployer(User user) {
        return repo.findByEmployer(user);
    }
}
