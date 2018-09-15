package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import ProjWEB.PROJWEB.Domain.Company;
import ProjWEB.PROJWEB.Domain.Dto.CompanySaveResponseDto;

public interface CompanyService {

    public List<Company> findAll() throws SQLException;
	
	public CompanySaveResponseDto save(Company company) throws SQLException,MessagingException ;
}
