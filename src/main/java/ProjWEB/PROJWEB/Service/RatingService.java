package ProjWEB.PROJWEB.Service;


import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Rating;

public interface RatingService {
	
	public List<Rating> findAll() throws SQLException;
	
	public int save(int ocena,long userId,long imageId) throws SQLException;
	
	public List<Rating> findByImageId(long imageId) throws SQLException;
	
	public List<Rating> findByUserId(long userId) throws SQLException;

}
