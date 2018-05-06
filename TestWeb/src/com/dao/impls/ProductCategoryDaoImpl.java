package com.dao.impls;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.beans.ProductCategory;
import com.daos.ProductCategoryDao;
import com.ntg.utils.ResultsSetUtil;
import com.utils.JdbcUtil;

public class ProductCategoryDaoImpl extends JdbcUtil  implements ProductCategoryDao{

	@Override
	public int add(ProductCategory t) {
		String sql ="insert into product_category(name,productId,type,iconClass) values(?,?,?,?)";
		Object [] object={t.getName(),t.getParentId(),t.getType(),t.getIconClass()};
		int result=0;
		try {
			result=executeUpdate(sql, object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Serializable t) {
		String sql="delete from product_category where id=?";
		int result=0;
		Object[] object={t};
		try {
			result=executeUpdate(sql, object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updata(ProductCategory t) {
		int result=0;
		String sql="update order_detail set name=?,parentId=?,type=?,iconClass=? where id=?";
		Object[] objects={t.getName(),t.getParentId(),t.getType(),t.getIconClass(),t.getId()};
		try {
			result=executeUpdate(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ProductCategory> select() {
		String sql="select * from product_category";
		List<ProductCategory > list =null;
		try {
			resultSet=executeQuery(sql);
			list=ResultsSetUtil.findAll(resultSet,ProductCategory.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductCategory findById(Serializable t) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
