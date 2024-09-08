package com.irisco.offerhuntermono.company;

import java.util.List;

public interface CompanyService {
	void createCompany(Company company);

	boolean updateCompanyById(Long id, Company updateCompany);

	List<Company> getAllCompanies();

	Company getCompanyById(Long id);

	boolean deleteCompanyById(Long id);
}
