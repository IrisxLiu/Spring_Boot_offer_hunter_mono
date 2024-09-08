package com.irisco.offerhuntermono.review;

import java.util.List;

public interface ReviewService {
	List<Review> getAllReviews(Long companyId);

	Review getReviewById(Long reviewId, Long companyId);

	boolean createReview(Long companyId, Review review);

	boolean updateReviewById(Long companyId, Long reviewId, Review updateReview);

	boolean deleteReviewById(Long companyId, Long reviewId);
}
