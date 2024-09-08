package com.irisco.offerhuntermono.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}")
public class ReviewController {
	ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
	}

	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
		Review review = reviewService.getReviewById(reviewId, companyId);
		if (review != null) {
			return new ResponseEntity<>(review, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/reviews")
	public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
		boolean created = reviewService.createReview(companyId, review);
		if (created) {
			return new ResponseEntity<>("Review added successfully!", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Company doesn't exist!", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId,
	                                               @RequestBody Review updateReview) {
		boolean updated = reviewService.updateReviewById(companyId, reviewId, updateReview);
		System.out.println(updated);
		if (updated) {
			return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
		boolean deleted = reviewService.deleteReviewById(companyId, reviewId);
		if (deleted) {
			return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
	}
}
