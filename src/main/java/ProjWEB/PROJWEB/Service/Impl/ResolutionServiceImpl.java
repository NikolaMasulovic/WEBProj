package ProjWEB.PROJWEB.Service.Impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;

import ProjWEB.PROJWEB.Dao.ResolutionDao;
import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.Dto.BuyImageDto;
import ProjWEB.PROJWEB.Domain.Dto.BuyResolutionDto;
import ProjWEB.PROJWEB.Service.ImageUtils;
import ProjWEB.PROJWEB.Service.MailService;
import ProjWEB.PROJWEB.Service.ResolutionService;

public class ResolutionServiceImpl implements ResolutionService {
	
	private ResolutionDao resolutionDao = new ResolutionDao();
	private ImageUtils imageUtils = new ImageUtils();
	private MailService mailService = new MailService();

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
		return resolutionDao.getResolutionsforImage(imageId, resolution);
	}

	@Override
	public boolean saveTest(List<Resolution> resolutions,long imageId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buy(BuyImageDto buyImageDto) throws SQLException {
		
		List<BuyResolutionDto> buyResolutions = buyImageDto.getResolutionsDto();
		List<Resolution> resolutions = new ArrayList<>();
		for (BuyResolutionDto buyResolutionDto : buyResolutions) {
			ArrayList<Resolution> imageResolutions = resolutionDao.getResolutionsforImage(buyResolutionDto.getImageId(), buyResolutionDto.getResolution());
			if(imageResolutions.size() > 0) {
				resolutions.add(imageResolutions.get(0));
			}else {
				System.out.println("IMAGE CAN NOT BE FOUND::IMAGE ID:"+buyResolutionDto.getImageId());
				return false;
			}		
			}
		try {
			mailService.sendWithAttachment(buyImageDto.getUserMail(), "You order", resolutions);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
//		ArrayList<Resolution> imageResolutions = resolutionDao.getResolutionsforImage(imageId, resolution);
//		BufferedImage imagefile = null;
//		boolean result = false;
//		
//		if(imageResolutions.size() > 0) {
//			Resolution imageResolution = imageResolutions.get(0);
//			try {
//				imagefile = ImageIO.read(new File(imageResolution.getBase64()));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return false;
//			}
//			mailService.sendWithAttachment(to, title);
//		}
//		
		return true;
	}

}
