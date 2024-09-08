package com.irisco.offerhuntermono.company.impl;

import com.irisco.offerhuntermono.company.Company;
import com.irisco.offerhuntermono.company.CompanyRepository;
import com.irisco.offerhuntermono.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public boolean updateCompanyById(Long id, Company updateCompany) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		if (companyOptional.isPresent()) {
			Company company = companyOptional.get();
			company.setName(updateCompany.getName());
			company.setDescription(updateCompany.getDescription());
			company.setLocation(updateCompany.getLocation());
			company.setMinEmployee(updateCompany.getMinEmployee());
			company.setMaxEmployee(updateCompany.getMaxEmployee());
			companyRepository.save(company);
			return true;
		}
		return false;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteCompanyById(Long id) {
//		Optional<Company> companyOptional = companyRepository.findById(id);
//		if (companyOptional.isPresent()) {
//			companyRepository.delete(companyOptional.get());
//			return true;
//		}
		if (companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
