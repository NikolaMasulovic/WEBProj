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

import ProjWEB.PROJWEB.Domain.Rating;
import ProjWEB.PROJWEB.Domain.Tag;
import ProjWEB.PROJWEB.Service.RatingService;
import ProjWEB.PROJWEB.Service.TagService;
import ProjWEB.PROJWEB.Service.Impl.RatingServiceImpl;
import ProjWEB.PROJWEB.Service.Impl.TagServiceImpl;

@Path("/tags")
public class TagApi {

	private TagService tagService = new TagServiceImpl();
	private RatingService ratingService = new RatingServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tag> getUsers() throws SQLException{
		List<Tag> tags = tagService.findAllTags();
		
		List<Rating> ratings = ratingService.findAll();
		for (Rating rating : ratings) {
			System.out.println("RATING::"+rating.toString());
		}
		
		return tags;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveTag")
	public Response login(Tag tag) throws SQLException {
		return Response.status(200).type("text/plain").entity(tagService.save(tag)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/images/{imageId}")
	public List<Tag> getTagsForImage(@PathParam("imageId") long imageId) throws SQLException{
		List<Tag> tags = tagService.getTagsForImage(imageId);
		return tags;
	}
	
}
