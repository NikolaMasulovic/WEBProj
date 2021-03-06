package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.ArrayList;
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
import ProjWEB.PROJWEB.Domain.Dto.FilterDto;
import ProjWEB.PROJWEB.Domain.Dto.ImagePageableDto;
import ProjWEB.PROJWEB.Domain.Dto.ImageUnapprovedDto;
import ProjWEB.PROJWEB.Domain.Dto.ImageUploadDto;
import ProjWEB.PROJWEB.Domain.Dto.SaveTestDto;
import ProjWEB.PROJWEB.Service.ImageService;
import ProjWEB.PROJWEB.Service.ResolutionService;
import ProjWEB.PROJWEB.Service.TagService;
import ProjWEB.PROJWEB.Service.Impl.ImageServiceImpl;
import ProjWEB.PROJWEB.Service.Impl.ResolutionServiceImpl;
import ProjWEB.PROJWEB.Service.Impl.TagServiceImpl;

@Path("/images")
public class ImageApi {
	
	private ImageService imageService = new ImageServiceImpl();
	private ResolutionService resolutionService = new ResolutionServiceImpl();
	private TagService tagService = new TagServiceImpl();

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all/{start}")
	public ImagePageableDto getAll(@PathParam("start") int start) throws SQLException{
		return imageService.findAllImages(start);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/save")
	public void save(Image image) throws SQLException {
		System.out.println("SAVE IMAGE API::");
		imageService.save(image);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/updateRate/{imageId}/{rate}")
	public int updateRate(@PathParam("imageId") long imageId,@PathParam("rate") int rate) throws SQLException {
		return imageService.updateRate(imageId, rate);
	}
	
	/**
	 * Filter
	 * @return
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/categoryCount")
	public int categoryCount() throws SQLException {
		return imageService.searchCategoryCount();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/categoryFilter/{searchFor}/{sortBy}/{direction}/{searchTerm}")
	public List<FilterDto> categoryFilter(@PathParam("searchFor") String searchFor,@PathParam("sortBy") String sortBy,@PathParam("direction") String direction,@PathParam("searchTerm") String searchTerm) throws SQLException {
		return imageService.searchCategory(searchFor,sortBy,direction,searchTerm);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/keywordFilter/{searchFor}/{sortBy}/{direction}/{searchTerm}")
	public List<FilterDto> keyWord(@PathParam("searchFor") String searchFor,@PathParam("sortBy") String sortBy,@PathParam("direction") String direction,@PathParam("searchTerm") String searchTerm) throws SQLException {
		return imageService.searchKeyword(searchFor,sortBy,direction,searchTerm);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/keywordFilterCount")
	public int keyWordCount() throws SQLException {
		return imageService.searchKeywordCount();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/usernameFilterCount")
	public int usernameFilterCount() throws SQLException {
		return imageService.searchUserCount();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usernameFilter/{searchFor}/{sortBy}/{direction}/{searchTerm}")
	public List<FilterDto> usernameFilter(@PathParam("searchFor") String searchFor,@PathParam("sortBy") String sortBy,@PathParam("direction") String direction,@PathParam("searchTerm") String searchTerm) throws SQLException {
		return imageService.searchUsername(searchFor,sortBy,direction,searchTerm);
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
	public Response saveSingleImage(ImageUploadDto saveTestDto) throws SQLException {
		return Response.status(200).type("text/plain").entity(imageService.uploadImage(saveTestDto)).build();
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
	@Path("/tagImage/{tagId}")
	public List<Image> getTagsForImage(@PathParam("tagId") long tagId) throws SQLException {
		return imageService.getImagesByTag(tagId,0);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/approveImage/{imageId}")
	public Response approveImage(@PathParam("imageId") long imageId) throws SQLException {
		System.out.println(imageId);
		return Response.status(200).type("text/plain").entity(imageService.approveImage(imageId)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{userId}")
	public ArrayList<Image> findByUserId(@PathParam("userId") long userId) throws SQLException {
		System.out.println(userId);
		return imageService.getImageForUser(userId,1);
	}
}
