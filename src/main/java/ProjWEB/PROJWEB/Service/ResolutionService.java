package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Resolution;

public interface ResolutionService {
	
	public List<Resolution> findAll() throws SQLException;
	
	public boolean save(Resolution resolution) throws SQLException;
	
	public ArrayList<Resolution> getResolutionsforImage(long imageId,String resolution) throws SQLException;
	
	public boolean saveTest(List<Resolution> resolutions,long imageId) throws SQLException;

}
