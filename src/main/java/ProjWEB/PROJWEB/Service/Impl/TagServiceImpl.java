package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Dao.TagDao;
import ProjWEB.PROJWEB.Domain.Tag;
import ProjWEB.PROJWEB.Service.TagService;

public class TagServiceImpl implements TagService{
	
	private TagDao tagDao = new TagDao();

	@Override
	public List<Tag> findAllTags() throws SQLException {
		return tagDao.findAll();
	}

	@Override
	public boolean save(Tag tag) throws SQLException {
		boolean result = false;
		if(tagDao.save(tag.getName()) > 0) result = true;
		return result;
	}

	@Override
	public List<Tag> getTagsForImage(long imageId) {
		
		List<Tag> tags = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		
		try {
			ids = tagDao.getTagBySlikaId(imageId);
			for (Long long1 : ids) {
				List<Tag> tg = tagDao.findTagById(long1);
				for (Tag tag : tg) {
					tags.add(tag);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tags;
	}

}
