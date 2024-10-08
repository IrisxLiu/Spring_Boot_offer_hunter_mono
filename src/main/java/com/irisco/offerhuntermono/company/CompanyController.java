package com.irisco.offerhuntermono.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company updateCompany) {
		boolean updated = companyService.updateCompanyById(id, updateCompany);
		if (updated) {
			return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {
		return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = companyService.getCompanyById(id);
		if (company == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(company, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
		boolean deleted = companyService.deleteCompanyById(id);
		if (deleted) {
			return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
	}
}
