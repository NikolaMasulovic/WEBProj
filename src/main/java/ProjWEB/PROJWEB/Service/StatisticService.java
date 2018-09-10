package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.Tag;
import ProjWEB.PROJWEB.Domain.Dto.ResolutionStatisticDto;
import ProjWEB.PROJWEB.Domain.Dto.UserStatisticDto;

public interface StatisticService {

	public List<Tag> categoryStatistic() throws SQLException;
	
	public List<ResolutionStatisticDto> resolutionStatistic() throws SQLException;
	
	public List<UserStatisticDto> userStatistic() throws SQLException;
}
