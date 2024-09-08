package com.irisco.offerhuntermono.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irisco.offerhuntermono.job.Job;
import com.irisco.offerhuntermono.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long minEmployee;
	private Long maxEmployee;
	private String location;
	private String description;

	// map by company in Job
	// this relation will be in job table, represented by company_id
	// instead of having a separate company_jobs table
	@JsonIgnore // ignore jobs info in json file to prevent infinite loop of company&jobs
	@OneToMany(mappedBy = "company")
	private List<Job> jobs;

	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<Review> reviews;

	public Company() {
	}

	public Company(Long id, String name, Long minEmployee, Long maxEmployee, String location,
	               String description, List<Job> jobs, List<Review> reviews) {
		this.id = id;
		this.name = name;
		this.minEmployee = minEmployee;
		this.maxEmployee = maxEmployee;
		this.location = location;
		this.description = description;
		this.jobs = jobs;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMinEmployee() {
		return minEmployee;
	}

	public void setMinEmployee(Long minEmployee) {
		this.minEmployee = minEmployee;
	}

	public Long getMaxEmployee() {
		return maxEmployee;
	}

	public void setMaxEmployee(Long maxEmployee) {
		this.maxEmployee = maxEmployee;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
