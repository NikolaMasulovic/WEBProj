package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Dao.ResolutionDao;
import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Service.ImageUtils;
import ProjWEB.PROJWEB.Service.ResolutionService;

public class ResolutionServiceImpl implements ResolutionService {
	
	private ResolutionDao resolutionDao = new ResolutionDao();
	private ImageUtils imageUtils = new ImageUtils();

	@Override
	public List<Resolution> findAll() throws SQLException {
		return resolutionDao.selectAll();
	}

	@Override
	public boolean save(Resolution resolution) throws SQLException {
		boolean result = false;
		if(resolutionDao.save(resolution) > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public ArrayList<Resolution> getResolutionsforImage(long imageId, String resolution) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveTest(List<Resolution> resolutions,long imageId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
