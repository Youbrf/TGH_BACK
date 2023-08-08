package be.icc.tgh.repository;

import be.icc.tgh.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewR extends JpaRepository<Review,Long> {
}
