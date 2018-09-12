package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.Dto.BuyImageDto;
import ProjWEB.PROJWEB.Domain.Dto.ImageUnapprovedDto;
import ProjWEB.PROJWEB.Domain.Dto.SaveTestDto;
import ProjWEB.PROJWEB.Service.ImageService;
import ProjWEB.PROJWEB.Service.ResolutionService;
import ProjWEB.PROJWEB.Service.Impl.ImageServiceImpl;
import ProjWEB.PROJWEB.Service.Impl.ResolutionServiceImpl;

@Path("/images")
public class ImageApi {
	
	private ImageService imageService = new ImageServiceImpl();
	private ResolutionService resolutionService = new ResolutionServiceImpl();

	
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
	
	/*
	 * RESOLUTIONS
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/resolutions")
	public List<Resolution> getResolutions() throws SQLException {
		return resolutionService.findAll();
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/resolutions/{imageId}/{resolution}")
//	public List<Resolution> getResolutionsForImageId(@PathParam("imageId") long imageId,@PathParam("resolution") String resolution) throws SQLException {
//		return resolutionService.getResolutionsforImage(imageId, resolution);
//	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buy")
	public Response buy(BuyImageDto buyImageDto) throws SQLException {
		return Response.status(200).type("text/plain").entity(resolutionService.buy(buyImageDto)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doTest")
	public Response doTest(SaveTestDto saveTestDto) throws SQLException {
		return Response.status(200).type("text/plain").entity(imageService.doTest(saveTestDto)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saveSingleImage")
	public Response saveSingleImage(SaveTestDto saveTestDto) throws SQLException {
		return Response.status(200).type("text/plain").entity(imageService.doTest(saveTestDto)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveResolution")
	public void saveResolution(Resolution resolution) throws SQLException {
		System.out.println("SAVE RESOLUTION API::");
		boolean result = resolutionService.save(resolution,"folderName","Name");
		System.out.println("RESOLUTION RESPONSE:"+result);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/approveTest/{userId}/admin/{adminId}")
	public Response approveTest(@PathParam("userId") long userId,@PathParam("adminId") long adminId) throws SQLException {
		System.out.println("user"+userId+"--admin"+adminId);
		return Response.status(200).type("text/plain").entity(imageService.approveTest(adminId, userId)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/seeAllUnapproved")
	public List<ImageUnapprovedDto> seeAllUnapproved() throws SQLException {
		return imageService.seeAllUnapproved();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/approveImage/{imageId}")
	public Response approveImage(@PathParam("imageId") long imageId) throws SQLException {
		System.out.println(imageId);
		return Response.status(200).type("text/plain").entity(imageService.approveImage(imageId)).build();
	}
}
