package be.icc.tgh.repository;

import be.icc.tgh.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurR extends JpaRepository<Administrateur,Long> {
}
