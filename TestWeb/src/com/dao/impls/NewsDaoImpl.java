package com.dao.impls;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daos.*;
import com.beans.*;
import com.ntg.utils.ResultsSetUtil;
import com.utils.*;

public class NewsDaoImpl extends JdbcUtil  implements NewsDao{
/**
 * 增加新闻
 */
	@Override
	public int add(News t) {
		String sql="insert into news(id,title,module,content,createTime,img) values(?,?,?,?,?,?)";
		Object[] objects={t.getId(),t.getTitle(),t.getModlue(),t.getContent(),t.getCreateTime(),t.getImg()};
		int result=0;
			try {
				result= this.executeUpdate(sql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
			
	}
/**
 * 删除新闻
 */
	@Override
	public int delete(Serializable t) {
		System.out.println("进入delete");
		String sql="delete from news where id=?";
		int result=0;
		try {
			result=this.executeUpdate(sql, t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
/**
 * 更新新闻
 */
	@Override
	public int updata(News t) {
		String sql="update news set title=?,module=?,content=?,createTime=?,img=? where id=?";
		Object[] objects={t.getTitle(),t.getModlue(),t.getContent(),t.getCreateTime(),t.getImg(),t.getId()};
		int result=0;
			try {
				result= this.executeUpdate(sql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
/**
 * 查询所有新闻
 */
	@Override
	public List<News> select() {
		String sql="select * from news";
		List<News> list=new ArrayList<>();
		try {
			ResultSet set=this.executeQuery(sql);
			list=ResultsSetUtil.findAll(set, News.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
/**
 * 查询单个新闻
 */
	@Override
	public News findById(Serializable t) {
		String sql ="select * from news where id=?";
		News news=new News();
		try {
			ResultSet set=	this.executeQuery(sql, t);
			news=ResultsSetUtil.findById(set, News.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return news;
	}
@Override
public int deletepic(Serializable t) {
	String sql="update news set img=''  where id=?";
	int result=0;
	try {
		result=this.executeUpdate(sql, t);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		close();
	}
	return result;
}
@Override
public String filePath(Serializable t) {
	String sql="select img from news where id=?";
	String path="";
	try {
		ResultSet  rs=this.executeQuery(sql, t);
		
		while(rs.next()){
			path=rs.getString("img");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		close();
	}
	return path;
}
@Override
public List<News> findAlls(int pageNum, int pageSize) {
	String sql = "select * from news limit ?,?";
	// 创建一个集合来保存所有的用户
	List<News> newss = new ArrayList<>();
	Object[] params = { pageNum, pageSize };
	try {
		ResultSet set = executeQuery(sql, params);
		newss = ResultsSetUtil.findAll(set, News.class);
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close();
	}
	return newss;
}
@Override
public int getTotalCount() {
	String sql = "select count(*) as count from news";
	int count = 0;
	try {
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			count = rs.getInt("count");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close();
	}
	return count;
}

}
