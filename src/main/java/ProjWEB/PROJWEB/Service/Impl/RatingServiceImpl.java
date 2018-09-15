package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Dao.RatingDao;
import ProjWEB.PROJWEB.Domain.Rating;
import ProjWEB.PROJWEB.Service.RatingService;

public class RatingServiceImpl implements RatingService {
	
	private RatingDao ratingDao = new RatingDao();

	@Override
	public List<Rating> findAll() throws SQLException {
		return ratingDao.selectAll();
	}

	@Override
	public int save(int ocena, long userId, long imageId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Rating> findByImageId(long imageId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rating> findByUserId(long userId)  throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

}
