package be.icc.tgh.repository;

import be.icc.tgh.model.Reservation;
import be.icc.tgh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationR extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM Reservation r WHERE r.dateReservation= ?2 AND r.employer.id = ?1 AND r.statutReservation IN ('CONFIRMED', 'PENDING')")
    List<Reservation> findReservationByEmployer(Integer id, LocalDate date);

    List<Reservation> findByUser(User user);

    List<Reservation> findByEmployer(User user);
}
