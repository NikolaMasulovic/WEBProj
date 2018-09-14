package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Dao.CompanyDao;
import ProjWEB.PROJWEB.Dao.UserDao;
import ProjWEB.PROJWEB.Domain.Company;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.CompanySaveResponseDto;
import ProjWEB.PROJWEB.Service.CompanyService;



public class CompanyServiceImpl implements CompanyService {

	private CompanyDao companyDao = new CompanyDao();
	private UserDao userDao = new UserDao();

	@Override
	public List<Company> findAll() throws SQLException {
		
		List<Company> companies = companyDao.selectAllCompanies();
		return companies;
	}

	@Override
	public CompanySaveResponseDto save(Company company) throws SQLException {
		
		User user = new User();
		user.setUsername(company.getName()+"Operator");
		user.setPassword("password1234");
		user.setEmail("");
		user.setDaily(0);
		user.setCountry("");
		user.setWeekly(0);
		user.setRole(4);
		Company companyResponse = companyDao.save(company);
		
		user.setCompanyId(companyResponse.getId());
		ArrayList<User> users = userDao.findAll();
		
		userDao.save(user);
		
		User u = userDao.getUserByCompanyId(company.getId());
		
		CompanySaveResponseDto csr = new CompanySaveResponseDto(u.getUsername(), u.getPassword());

		return csr;
		
	}

}
