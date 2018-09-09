package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Dao.TagDao;
import ProjWEB.PROJWEB.Domain.Tag;
import ProjWEB.PROJWEB.Service.StatisticService;

public class StatisticServiceimpl implements StatisticService {
	
	private TagDao tagDao = new TagDao();

	@Override
	public List<Tag> categoryStatistic() throws SQLException {
		return tagDao.tagStatistic();
	}

}
