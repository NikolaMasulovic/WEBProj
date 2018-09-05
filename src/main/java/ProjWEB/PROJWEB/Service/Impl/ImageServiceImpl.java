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
import ProjWEB.PROJWEB.Dao.UserDao;
import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.SaveTestDto;
import ProjWEB.PROJWEB.Service.ImageService;
import ProjWEB.PROJWEB.Service.ImageUtils;
import ProjWEB.PROJWEB.Service.ResolutionService;

public class ImageServiceImpl implements ImageService{
	
	private ImageDao imageDao = new ImageDao();
	private ImageUtils imageUtils = new ImageUtils();
	private ResolutionService resolutionService = new ResolutionServiceImpl();
	private UserDao userDao = new UserDao();


	/*
	 * vraca sve slike i pravi im base64
	 * (non-Javadoc)
	 * @see ProjWEB.PROJWEB.Service.ImageService#findAllImages()
	 */
	@Override
	public List<Image> findAllImages() throws SQLException {
		List<Image> forretrun = new ArrayList<>();
		List<Image> forretrun1 = imageDao.findAll();
//		BufferedImage imagefile = null;
//		for (Image image : forretrun1) {
//				try {
//					imagefile = ImageIO.read(new File(image.getPath()));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					System.out.println("GETTING IMAGE FROM FILE ERROR::");
//					e.printStackTrace();
//					continue;
//				}
//				image.setUrl(ImageUtils.base64FromImage(imagefile));
//				forretrun.add(image);
//		}
		return forretrun1;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setPath("/Users/mac/Desktop/webDir/"+folderName+"/"+imageName+".png");
		int result = imageDao.save(image);
		System.out.println("IMAGE SAVE RESULT::"+result);
		return result;
	}

	@Override
	public ArrayList<Image> getImageForUser(long userId, int approved) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approve(long userId, int approve) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Image> getImagesByTag(long tagId, int page) {
		// TODO Auto-generated method stub
		return null;
	}
	
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
		
		
		
		Image img = new Image(0, 0, datePublished, 0, name+"WATERMARK", "", description,userId, "", 0);
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
	
}
