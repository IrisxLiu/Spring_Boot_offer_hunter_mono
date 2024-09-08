package com.irisco.offerhuntermono.review.impl;

import com.irisco.offerhuntermono.company.Company;
import com.irisco.offerhuntermono.company.CompanyService;
import com.irisco.offerhuntermono.review.Review;
import com.irisco.offerhuntermono.review.ReviewRepository;
import com.irisco.offerhuntermono.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;
	private final CompanyService companyService;

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public Review getReviewById(Long reviewId, Long companyId) {
		// get all reviews of a company
		System.out.println("reviewid" + reviewId);
		System.out.println("companyid" + companyId);
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		System.out.println(reviews);
		// stream the list, filter by id, find and return the first result, or return null
		return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public boolean createReview(Long companyId, Review review) {
		// get company by id using company service
		Company company = companyService.getCompanyById(companyId);
		// set company to review
		if (company != null) {
			review.setCompany(company);
			// save review to db
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateReviewById(Long companyId, Long reviewId, Review updateReview) {
		Review review = getReviewById(reviewId, companyId);
		System.out.println(review);
		if (review != null) {
			review.setTitle(updateReview.getTitle());
			review.setDescription(updateReview.getDescription());
			review.setRating(updateReview.getRating());
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReviewById(Long companyId, Long reviewId) {
		Review review = getReviewById(reviewId, companyId);
		if (review != null) {
			reviewRepository.delete(review);
			return true;
		}
		return false;
	}
}
