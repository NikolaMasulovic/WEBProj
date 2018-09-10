package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Dao.ImageDao;
import ProjWEB.PROJWEB.Dao.ResolutionDao;
import ProjWEB.PROJWEB.Dao.TagDao;
import ProjWEB.PROJWEB.Dao.UserDao;
import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.Tag;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.ResolutionStatisticDto;
import ProjWEB.PROJWEB.Domain.Dto.UserStatisticDto;
import ProjWEB.PROJWEB.Service.StatisticService;

public class StatisticServiceimpl implements StatisticService {
	
	private TagDao tagDao = new TagDao();
	private ResolutionDao resolutionDao = new ResolutionDao();
	private ImageDao imageDao = new ImageDao();
	private UserDao userDao = new UserDao();
	

	@Override
	public List<Tag> categoryStatistic() throws SQLException {
		return tagDao.tagStatistic();
	}

	@Override
	public List<ResolutionStatisticDto> resolutionStatistic() throws SQLException {
		return resolutionDao.resolutionStatistic();
	}

	@Override
	public List<UserStatisticDto> userStatistic() throws SQLException {
		
		List<UserStatisticDto> listForReturn = new ArrayList<>();
		
		List<User> users = userDao.findAll();
		for (User user : users) {
			UserStatisticDto userDto = new UserStatisticDto();
			ArrayList<Image> imagesForUser = imageDao.findAllByUserId(user.getId(), 1);
			int countHD =0;
			int countUHD =0;
			int count4K =0;
			for (Image image : imagesForUser) {
				List<Resolution> resHD = resolutionDao.getResolutionsforImage(image.getId(), "HD");
				List<Resolution> resUHD = resolutionDao.getResolutionsforImage(image.getId(), "UHD");
				List<Resolution> res4K = resolutionDao.getResolutionsforImage(image.getId(), "4K");
				
				for (Resolution resolutionHd : resHD) {
					countHD += resolutionHd.getCount();
				}
                for (Resolution resolutionUhd : resUHD) {
                	countUHD += resolutionUhd.getCount();
				}
                for (Resolution resolution4k : res4K) {
                	count4K += resolution4k.getCount();
                }
			}
			userDto.setUser(user);
			userDto.setCountHD(countHD);
			userDto.setCountUHD(countUHD);
			userDto.setCount4K(count4K);
			listForReturn.add(userDto);
		}
		return listForReturn;
	}

}
