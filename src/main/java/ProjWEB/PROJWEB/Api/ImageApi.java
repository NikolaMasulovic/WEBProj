package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Service.ImageService;
import ProjWEB.PROJWEB.Service.Impl.ImageServiceImpl;

@Path("/images")
public class ImageApi {
	
	private ImageService imageService = new ImageServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> getAll() throws SQLException{
		return imageService.findAllImages();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/save")
	public void save(Image image) throws SQLException {
		System.out.println("SAVE IMAGE API::");
		imageService.save(image);
	}
	

}
