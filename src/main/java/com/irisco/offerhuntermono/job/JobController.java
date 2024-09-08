package com.irisco.offerhuntermono.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
	private JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping
	public ResponseEntity<List<Job>> getAllJobs() {
//		return ResponseEntity.ok(jobService.getAllJobs());
		return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("Job added successfully!", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		Job job = jobService.getJobById(id);
		if (job == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(job, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
		boolean deleted = jobService.deleteJobById(id);
		if (deleted) {
			return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob) {
		boolean updated = jobService.updateJobById(id, updatedJob);
		if (updated) {
			return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
