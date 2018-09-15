package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.Company;
import ProjWEB.PROJWEB.Domain.Dto.CompanySaveResponseDto;
import ProjWEB.PROJWEB.Service.CompanyService;
import ProjWEB.PROJWEB.Service.Impl.CompanyServiceImpl;

@Path("/company")
public class CompanyApi {
	
    private CompanyService companyService = new CompanyServiceImpl();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> getAllImage() throws SQLException {
		return companyService.findAll();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	public CompanySaveResponseDto save(Company company) throws SQLException, MessagingException {
		return companyService.save(company);
	}

}
