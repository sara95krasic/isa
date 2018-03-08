package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Theater;
import rs.ac.uns.ftn.informatika.jpa.domain.Review;

public interface ReviewRepository extends Repository<Review, Long> {

	Page<Review> findByTheater(Theater theater, Pageable pageable);

	Review save(Review review);

}
