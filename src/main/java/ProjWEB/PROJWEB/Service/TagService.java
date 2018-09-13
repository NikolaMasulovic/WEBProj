package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Tag;

public interface TagService {
	
	public List<Tag> findAllTags() throws SQLException;
	
	public boolean save(Tag tag) throws SQLException;
	
	public List<Tag> getTagsForImage(long imageId);


}
