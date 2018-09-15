package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Rating;
import ProjWEB.PROJWEB.Service.RatingService;
import ProjWEB.PROJWEB.Service.Impl.RatingServiceImpl;


@Path("/rating")
public class RatingApi {
	
	private RatingService ratingService = new RatingServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rating> getUsers() throws SQLException{
		
		List<Image> images = ratingService.findAllImages();
		for (Image image : images) {
			System.out.println("IMAGE--"+image.toString());
		}
		
		return ratingService.findAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/image/{imageId}")
	public List<Rating> findByImage(@PathParam("imageId") long imageId) throws SQLException{
		return ratingService.findByImageId(imageId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{userId}")
	public List<Rating> findByUser(@PathParam("userId") long userId) throws SQLException{
		return ratingService.findByUserId(userId);
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/save")
	public int save(Rating rating) throws SQLException{
		return ratingService.save(rating.getOcena(), rating.getUserId(), rating.getImageId());
	}

}
