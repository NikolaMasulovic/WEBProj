package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Comment;

public interface CommentService {
	
    public List<Comment> findAll() throws SQLException;
	
	public int save(Comment comment) throws SQLException;
	
	public List<Comment> findByImageId(long imageId) throws SQLException;
	
	public List<Comment> findByUserId(long userId) throws SQLException;
	
	//public List<Image> findAllImages() throws SQLException;


}
