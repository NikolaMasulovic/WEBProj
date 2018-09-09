package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Tag;

public interface StatisticService {

	public List<Tag> categoryStatistic() throws SQLException;
}
