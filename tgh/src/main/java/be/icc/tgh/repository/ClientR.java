package be.icc.tgh.repository;

import be.icc.tgh.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientR extends JpaRepository<Client,Long> {
}
