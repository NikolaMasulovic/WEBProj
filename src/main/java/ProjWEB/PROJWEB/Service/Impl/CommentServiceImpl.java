package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ProjWEB.PROJWEB.Dao.CommentDao;
import ProjWEB.PROJWEB.Domain.Comment;
import ProjWEB.PROJWEB.Service.CommentService;

public class CommentServiceImpl implements CommentService {
	
	private CommentDao commentDao = new CommentDao();

	@Override
	public List<Comment> findAll() throws SQLException {
		return commentDao.selectAll();
	}

	@Override
	public int save(Comment comment) throws SQLException {
		
		//danasnji datum za save comment
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datePublished = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		comment.setDate(datePublished);
		
		return commentDao.save(comment);
	}

	@Override
	public List<Comment> findByImageId(long imageId) throws SQLException {
		return commentDao.findAllByImageId(imageId);
	}

	@Override
	public List<Comment> findByUserId(long userId) throws SQLException {
		return commentDao.findAllByUserId(userId);
	}

}
