package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Image;
public interface ImageService {

	public List<Image> findAllImages() throws SQLException ;
   
	public boolean save(Image image);
	
	//public List<ImageDto> getAllImages(long startId,long endId);
	
	//public boolean test(ImageSaveDto imageDto,int time);
	
	//public PaginationDto findAllByApproved(int approvedNum,int startId);
	
	//public ImageAdminDto getAllForAdmin()  throws SQLException ;
	
	public ArrayList<Image> getImageForUser(long userId,int approved);
	
	public boolean approve(long userId,int approve);
	
	public List<Image> getImagesByTag(long tagId,int page);
	
	//public int saveTest(List<ImageSaveDto> dtos);
}
