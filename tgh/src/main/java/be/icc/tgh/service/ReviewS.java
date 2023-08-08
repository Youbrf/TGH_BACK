package be.icc.tgh.service;

import be.icc.tgh.model.Review;
import be.icc.tgh.repository.ReviewR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewS {
    @Autowired
    private ReviewR repo;

    public Review creerReview(Review review) {
       return repo.save(review);
    }
}
