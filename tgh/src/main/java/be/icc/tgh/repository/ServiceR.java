package be.icc.tgh.repository;

import be.icc.tgh.model.CategorieService;
import be.icc.tgh.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceR extends JpaRepository<Service,Long> {
    List<Service> findByCategorieId(Long id);
}
