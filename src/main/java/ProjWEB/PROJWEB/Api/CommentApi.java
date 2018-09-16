package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.Comment;
import ProjWEB.PROJWEB.Service.CommentService;
import ProjWEB.PROJWEB.Service.Impl.CommentServiceImpl;

@Path("/comment")
public class CommentApi {
	
    private CommentService commentService = new CommentServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getUsers() throws SQLException{
		return commentService.findAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/image/{imageId}")
	public List<Comment> findByImage(@PathParam("imageId") long imageId) throws SQLException{
		return commentService.findByImageId(imageId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{userId}")
	public List<Comment> findByUser(@PathParam("userId") long userId) throws SQLException{
		return commentService.findByUserId(userId);
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/save")
	public int save(Comment comment) throws SQLException{
		return commentService.save(comment);
	}

}
