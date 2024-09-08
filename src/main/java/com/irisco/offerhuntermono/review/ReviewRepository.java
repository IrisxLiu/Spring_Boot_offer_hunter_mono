package com.irisco.offerhuntermono.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	// jpa translates it to query language, we don't need to implement the method
	// SELECT * FROM review WHERE company_id = ?;
	// reviewRepository.findByCompanyId(1L)
	// SELECT * FROM review WHERE company_id = 1;
	List<Review> findByCompanyId(Long companyId);
}
