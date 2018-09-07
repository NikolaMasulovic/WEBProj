package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
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

}
