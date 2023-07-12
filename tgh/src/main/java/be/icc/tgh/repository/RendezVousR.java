package be.icc.tgh.repository;

import be.icc.tgh.model.RendezVous;
import be.icc.tgh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RendezVousR extends JpaRepository<RendezVous,Long> {
    List<RendezVous> findByDateReservation(LocalDate date);

    List<RendezVous> findByUser(User user);

    List<RendezVous> findByEmployer(User user);
}
