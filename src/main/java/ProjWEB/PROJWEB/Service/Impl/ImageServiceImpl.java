package ProjWEB.PROJWEB.Service.Impl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import ProjWEB.PROJWEB.Dao.ImageDao;
import ProjWEB.PROJWEB.Dao.RatingDao;
import ProjWEB.PROJWEB.Dao.TagDao;
import ProjWEB.PROJWEB.Dao.UserDao;
import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Rating;
import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.Tag;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.FilterDto;
import ProjWEB.PROJWEB.Domain.Dto.ImagePageableDto;
import ProjWEB.PROJWEB.Domain.Dto.ImageUnapprovedDto;
import ProjWEB.PROJWEB.Domain.Dto.ImageUploadDto;
import ProjWEB.PROJWEB.Domain.Dto.SaveTestDto;
import ProjWEB.PROJWEB.Service.ImageService;
import ProjWEB.PROJWEB.Service.ImageUtils;
import ProjWEB.PROJWEB.Service.ResolutionService;

public class ImageServiceImpl implements ImageService{
	
	private ImageDao imageDao = new ImageDao();
	private ImageUtils imageUtils = new ImageUtils();
	private ResolutionService resolutionService = new ResolutionServiceImpl();
	private UserDao userDao = new UserDao();
	private TagDao tagDao = new TagDao();
	private RatingDao ratingDao = new RatingDao();


	/*
	 * vraca sve slike i pravi im base64
	 * (non-Javadoc)
	 * @see ProjWEB.PROJWEB.Service.ImageService#findAllImages()
	 */
	@Override
	public ImagePageableDto findAllImages(int start) throws SQLException {
		ImagePageableDto pageable = new ImagePageableDto();
		int imagecount = imageDao.findAllCount();
		pageable.setCount(imagecount);
		List<Image> forretrun = new ArrayList<>();
		List<Image> forretrun1 = imageDao.home(1,start);
		BufferedImage imagefile = null;
		for (Image image : forretrun1) {
				try {
					imagefile = ImageIO.read(new File(image.getPath()));
				} catch (IOException e) {
					System.out.println("GETTING IMAGE FROM FILE ERROR::ID "+image.getId());
					e.printStackTrace();
					continue;
				}
				String url64 = "data:image/jpeg;base64,"+ImageUtils.base64FromImage(imagefile);
				image.setUrl(url64);
				forretrun.add(image);
		}
		pageable.setImages(forretrun);
		return pageable;
	}

	/*
	 * 1)proveri direktorijum
	 * 2)ako ima sacuvaj sliku file u njega(ime slike+tumb)+watermark i onda sacuvaj u bazi
	 * ako nema foldera napravi se pa se sacuva
	 * 3)retrun
	 */
	@Override
	public int save(Image image) throws SQLException{
		System.out.println("Image name::"+image.getName());
		
		ArrayList<User> u = userDao.findUserById(image.getUserId());
		String folderName = u.get(0).getUsername();
		String imageName = image.getName();
		
		//1
        File theDir = new File("/Users/mac/Desktop/webDir/"+folderName);
		if (!theDir.exists()) {
		    System.out.println("CREATING DIRECTORY FOR:" + theDir.getName());
		    boolean result = false;
		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		    }        
		    if(result) {    
		        System.out.println("DIR CREATED"); 
		    }
		}
		
		BufferedImage img = imageUtils.imageFromBase64(image.getUrl());
		img = imageUtils.scaleImage(img, 0.2);
		img = addTextWatermark("WATERMARK", img);
		File outputfile = new File("/Users/mac/Desktop/webDir/"+folderName+"/"+imageName+".png");
		
		try {
			ImageIO.write(img, "png", outputfile);
			System.out.println("IMAGE AS FILE SAVED::");
		} catch (IOException e) {
			System.out.println("SAVE IMAGE AS FILE ERROR::");
			e.printStackTrace();
		}
		image.setPath("/Users/mac/Desktop/webDir/"+folderName+"/"+imageName+".png");
		int result = imageDao.save(image);
		System.out.println("IMAGE SAVE RESULT::"+result);
		return result;
	}

	@Override
	public ArrayList<Image> getImageForUser(long userId, int approved) throws SQLException {
		ImagePageableDto pageable = new ImagePageableDto();
		int imagecount = imageDao.findAllCount();
		pageable.setCount(imagecount);
		List<Image> forretrun = new ArrayList<>();
		List<Image> forretrun1 = imageDao.findAllByUserId(userId, 1);
		BufferedImage imagefile = null;
		for (Image image : forretrun1) {
				try {
					imagefile = ImageIO.read(new File(image.getPath()));
				} catch (IOException e) {
					System.out.println("GETTING IMAGE FROM FILE ERROR::ID "+image.getId());
					e.printStackTrace();
					continue;
				}
				String url64 = "data:image/jpeg;base64,"+ImageUtils.base64FromImage(imagefile);
				image.setUrl(url64);
				forretrun.add(image);
		}
		
		return (ArrayList<Image>) forretrun;
	}

	@Override
	public boolean approve(long userId, int approve) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Image> getImagesByTag(long tagId, int page) {
		
		List<Image> images = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		
		try {
			ids = tagDao.getImagesByTagId(tagId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Long l : ids) {
			List<Image> imgs = new ArrayList<>();
			try {
				imgs = imageDao.findImageById(l);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Image i : imgs) {
				images.add(i);
			}
		}
		
		BufferedImage imageB = null;
		for (Image im : images) {
			try {
				imageB = ImageIO.read(new File(im.getPath()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String base = ImageUtils.base64FromImage(imageB);
			im.setUrl("data:image/png;base64,"+base);
		}
		return images;
	}
		
	//}
	
	/*
	 * dodaje watermark
	 */
	public BufferedImage addTextWatermark(String text, BufferedImage sourceImage) {
	    Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
 
		// initializes necessary graphic properties
		AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
		g2d.setComposite(alphaChannel);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.BOLD, 84));
		FontMetrics fontMetrics = g2d.getFontMetrics();
		Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
 
		// calculates the coordinate where the String is painted
		int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
		int centerY = sourceImage.getHeight() / 2;
 
		// paints the textual watermark
		g2d.drawString(text, centerX, centerY);
		g2d.drawString(text, centerX, centerY);
		g2d.drawString(text, centerX, centerY);
		g2d.drawString(text, centerX, centerY-200);
		g2d.drawString(text, centerX, centerY-200);
		g2d.drawString(text, centerX, centerY-200);
		g2d.drawString(text, centerX, centerY+200);
		g2d.drawString(text, centerX, centerY+200);
		g2d.drawString(text, centerX, centerY+200);
 
		//ImageIO.write(sourceImage, "png", destImageFile);
		g2d.dispose();
 
		System.out.println("The tex watermark is added to the image.");
		return sourceImage;
	}

	@Override
	public boolean doTest(SaveTestDto testSaveDto) throws SQLException {
		
		String name = testSaveDto.getName();
		String description = testSaveDto.getDescription();
		List<Resolution> resolutions = testSaveDto.getResolutions();
		long userId = testSaveDto.getUserId();
		
		ArrayList<User> u = userDao.findUserById(userId);
		String folderName = u.get(0).getUsername();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datePublished = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		
		
		
		Image img = new Image(0, 0, datePublished, 0, name, "", description,userId, "", 0,0.0);
		img.setUrl(resolutions.get(0).getBase64());
		int imageId =  save(img);
		
		for (Resolution resolution : resolutions) {
			resolution.setSlikaId(imageId);
			if(!resolutionService.save(resolution,folderName,testSaveDto.getName())) {
				return false;
			}
			System.out.println("Uspesno sacuvano.");	
		}
		
		return true;
	}

	@Override
	public boolean approveTest(long adminId, long userId) throws SQLException {
	
		ArrayList<User> u = userDao.findUserById(adminId);
		System.out.println("user"+userId+"--admin"+adminId);
		int role = u.get(0).getRole();
		if(role == 1) {
			int result = imageDao.approveTest(userId);
			if(result == 3) {
				System.out.println("Uspesan approve");
			}
		}else {
			System.out.println("YOU ARE NOT OPERATER::");
			return false;
		}
		
		return true;
	}

	@Override
	public List<ImageUnapprovedDto> seeAllUnapproved() throws SQLException {
		
		ArrayList<User> users = userDao.findAll();
		List<ImageUnapprovedDto> unapprovedDtos = new ArrayList<>();
		
		for (User user : users) {
			ArrayList<Image> images = imageDao.findAllByUserId(user.getId(), 0);
			ImageUnapprovedDto imageUnADto = new ImageUnapprovedDto();
			if(images.size() > 0) {
				imageUnADto.setUser(user);
				BufferedImage imagefile = null;
				//get res
				ArrayList<Resolution> resolutions = new ArrayList<>();
				for (Image image : images) {
					ArrayList<Resolution> res = resolutionService.getResolutionsforImage(image.getId(), "HD");
						try {
							imagefile = ImageIO.read(new File(res.get(0).getPath()));//prebaciti u res service
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.println("GETTING IMAGE FROM FILE ERROR::");
							e.printStackTrace();
							continue;
						}
						res.get(0).setBase64(ImageUtils.base64FromImage(imagefile));
						resolutions.add(res.get(0));
//						forretrun.add(image);
			}
				imageUnADto.setImages(resolutions);
				unapprovedDtos.add(imageUnADto);
			}
			
		}
		
		return unapprovedDtos;
	}

	@Override
	public boolean approveImage(long imageId) throws SQLException {
		if(imageDao.approveImage(imageId) > 0) return true;
		return false;
	}

	@Override
	public boolean uploadImage(ImageUploadDto imageUploadDto) throws SQLException {
		String name = imageUploadDto.getName();
		String description = imageUploadDto.getDescription();
		List<Resolution> resolutions = imageUploadDto.getResolutions();
		long userId = imageUploadDto.getUserId();
		
		ArrayList<User> u = userDao.findUserById(userId);
		String folderName = u.get(0).getUsername();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datePublished = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		
		
		
		Image img = new Image(0, 0, datePublished, 0, name, "", description,userId, "", 0,0.0);
		img.setUrl(resolutions.get(0).getBase64());
		int imageId =  save(img);
		
		for (Resolution resolution : resolutions) {
			resolution.setSlikaId(imageId);
			if(!resolutionService.save(resolution,folderName,imageUploadDto.getName())) {
				return false;
			}
			System.out.println("Uspesno sacuvano.");	
		}
		
		ArrayList<Tag> tags = tagDao.findTagById(2);
		Tag tag = tags.get(0);
		int tg = tagDao.saveTagImage(tag.getId(), imageId);
		System.out.println("TAG::"+tg);
		
		return true;
	}

	@Override
	public int updateRate(long imageId, int rate) throws SQLException {
		
		//danasnji datum za save rating
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datePublished = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		
		List<Image> imageList = imageDao.findImageById(imageId);
		Image image = imageList.get(0);
		List<Rating> ratings = ratingDao.findAllByImageId(image.getId());
		
		double ratingDb = 0;
		int ocena =0;
		int ukupno =0;
		for (Rating rating : ratings) {
			ocena += rating.getOcena();
			ukupno++;
		}
		ocena = ocena+rate;
		ukupno = ukupno+1;
		ratingDb = (double)ocena/(double)(ukupno);
		System.out.println("ocena/ukupno"+ocena+"/"+ukupno);
		System.out.println("DOUBLE"+ratingDb);
		
		//save rating
		Rating ratingApi = new Rating(0, rate, datePublished, 1, imageId);
		ratingDao.save(ratingApi);

		return imageDao.updateRate(imageId, ratingDb);
	}

	@Override
	public int searchCategoryCount() {
		return imageDao.searchCategoryCount("", "", "", "Priroda");
	}

	@Override
	public List<FilterDto> searchCategory(String searchFor, String sortBy, String direction, String searchTerm) {
		List<FilterDto> filterImages = imageDao.searchCategory(searchFor, sortBy, direction, searchTerm,10, 1);
		
		BufferedImage imagefile = null;

		for (FilterDto filterDto : filterImages) {
			try {
				imagefile = ImageIO.read(new File(filterDto.getPath()));
			} catch (IOException e) {
				System.out.println("GETTING IMAGE FROM FILE ERROR::ID "+filterDto.getPhotoId());
				e.printStackTrace();
				continue;
			}
			String url64 = "data:image/jpeg;base64,"+ImageUtils.base64FromImage(imagefile);
			filterDto.setBase64(url64);
		}
		return filterImages;
	}

	@Override
	public int searchKeywordCount() {
		return imageDao.searchKeywordCount("", "", "", "Livada");
	}

	@Override
	public List<FilterDto> searchKeyword(String searchFor, String sortBy, String direction, String searchTerm) {
	List<FilterDto> filterImages = imageDao.searchKeyword(searchFor, sortBy, direction, searchTerm,10, 1);
		
		BufferedImage imagefile = null;

		for (FilterDto filterDto : filterImages) {
			try {
				imagefile = ImageIO.read(new File(filterDto.getPath()));
			} catch (IOException e) {
				System.out.println("GETTING IMAGE FROM FILE ERROR::ID "+filterDto.getPhotoId());
				e.printStackTrace();
				continue;
			}
			String url64 = "data:image/jpeg;base64,"+ImageUtils.base64FromImage(imagefile);
			filterDto.setBase64(url64);
		}
		return filterImages;
	}

	@Override
	public int searchUserCount() {
		return imageDao.searchKeywordCount("username", "", "", "mike");
	}

	@Override
	public List<FilterDto> searchUsername(String searchFor, String sortBy, String direction, String searchTerm) {
    List<FilterDto> filterImages = imageDao.searchUserName(searchFor, sortBy, direction, searchTerm,10, 1);
		
		BufferedImage imagefile = null;

		for (FilterDto filterDto : filterImages) {
			try {
				imagefile = ImageIO.read(new File(filterDto.getPath()));
			} catch (IOException e) {
				System.out.println("GETTING IMAGE FROM FILE ERROR::ID "+filterDto.getPhotoId());
				e.printStackTrace();
				continue;
			}
			String url64 = "data:image/jpeg;base64,"+ImageUtils.base64FromImage(imagefile);
			filterDto.setBase64(url64);
		}
		return filterImages;
	}
	
}
