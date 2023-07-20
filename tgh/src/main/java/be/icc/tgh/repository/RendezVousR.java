package be.icc.tgh.repository;

import be.icc.tgh.model.RendezVous;
import be.icc.tgh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RendezVousR extends JpaRepository<RendezVous,Long> {
    @Query("SELECT r FROM RendezVous r WHERE r.dateReservation= ?2 AND r.employer.id = ?1 AND r.statutReservation IN ('CONFIRMED', 'PENDING')")
    List<RendezVous> findReservationByEmployer(Integer id, LocalDate date);

    List<RendezVous> findByUser(User user);

    List<RendezVous> findByEmployer(User user);
}
