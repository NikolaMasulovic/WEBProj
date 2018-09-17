package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Dto.FilterDto;
import ProjWEB.PROJWEB.Domain.Dto.ImagePageableDto;
import ProjWEB.PROJWEB.Domain.Dto.ImageUnapprovedDto;
import ProjWEB.PROJWEB.Domain.Dto.ImageUploadDto;
import ProjWEB.PROJWEB.Domain.Dto.SaveTestDto;
public interface ImageService {

	public ImagePageableDto findAllImages(int start) throws SQLException ;
   
	public int save(Image image) throws SQLException;
	
	public boolean doTest(SaveTestDto testSaveDto) throws SQLException;
	
	public boolean uploadImage(ImageUploadDto imageUploadDto) throws SQLException;

		
	//public List<ImageDto> getAllImages(long startId,long endId);
	
	//public boolean test(ImageSaveDto imageDto,int time);
	
	//public PaginationDto findAllByApproved(int approvedNum,int startId);
	
	//public ImageAdminDto getAllForAdmin()  throws SQLException ;
	
	public ArrayList<Image> getImageForUser(long userId,int approved) throws SQLException;
	
	public boolean approve(long userId,int approve);
	
	public boolean approveTest(long adminId,long userId) throws SQLException;
	
	public List<Image> getImagesByTag(long tagId,int page);
	
	public List<ImageUnapprovedDto> seeAllUnapproved() throws SQLException;
	
	public boolean approveImage(long imageId) throws SQLException;
	
	public int updateRate(long imageId,int rate) throws SQLException;
	
	public int searchCategoryCount();
	
	public List<FilterDto> searchCategory();
	
	//public int saveTest(List<ImageSaveDto> dtos);
}
