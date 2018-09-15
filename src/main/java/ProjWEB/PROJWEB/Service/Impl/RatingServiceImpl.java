package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ProjWEB.PROJWEB.Dao.ImageDao;
import ProjWEB.PROJWEB.Dao.RatingDao;
import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Rating;
import ProjWEB.PROJWEB.Service.RatingService;

public class RatingServiceImpl implements RatingService {
	
	private RatingDao ratingDao = new RatingDao();
	private ImageDao imageDao = new ImageDao();

	@Override
	public List<Rating> findAll() throws SQLException {
		return ratingDao.selectAll();
	}

	@Override
	public int save(int ocena, long userId, long imageId) throws SQLException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datePublished = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		
		Rating rating = new Rating(0, ocena, datePublished, userId, imageId);
		return ratingDao.save(rating);
	}

	@Override
	public List<Rating> findByImageId(long imageId) throws SQLException {
		return ratingDao.findAllByImageId(imageId);
	}

	@Override
	public List<Rating> findByUserId(long userId)  throws SQLException{
		return ratingDao.findAllByUserId(userId);
	}

	@Override
	public List<Image> findAllImages() throws SQLException {
		return imageDao.findAll();
	}

}
