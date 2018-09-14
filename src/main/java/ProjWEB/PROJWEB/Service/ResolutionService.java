package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.Dto.BuyImageDto;

public interface ResolutionService {
	
	public List<Resolution> findAll() throws SQLException;
	
	public boolean save(Resolution resolution,String folderName,String name) throws SQLException;
	
	public ArrayList<Resolution> getResolutionsforImage(long imageId,String resolution) throws SQLException;
	
	public boolean buy(BuyImageDto buyImageDto) throws SQLException;
	
	public boolean saveTest(List<Resolution> resolutions,long imageId) throws SQLException;
	
	public String getResolutionForImage(long imageId, String res);

}
