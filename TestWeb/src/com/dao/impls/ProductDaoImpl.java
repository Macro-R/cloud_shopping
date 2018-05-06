	package com.dao.impls;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.beans.Product;
import com.daos.ProductDao;
import com.ntg.utils.ResultsSetUtil;
import com.utils.JdbcUtil;

public class ProductDaoImpl extends JdbcUtil  implements ProductDao{
	@Override
	public int add(Product t) {
		String sql ="insert into product(name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id) values(?,?,?,?，?,?,?,?，?,?)";
		Object [] object={t.getName(),t.getDescription(),t.getPrice(),t.getStock(),t.getCategoryLevel1id(),t.getCategoryLevel2id(),t.getCategoryLevel3id()};
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
		String sql="delete from product where id=?";
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
	public int updata(Product t) {
		int result=0;
		String sql="update product set name=?,description=?,price=?,stock=? where id=?";
		Object[] objects={t.getName(),t.getDescription(),t.getPrice(),t.getStock(),t.getId()};
		try {
			result=executeUpdate(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Product> select() {
		String sql="select * from product limit 0,10";
		List<Product > list =null;
		try {
			
			resultSet=executeQuery(sql);
			list=ResultsSetUtil.findAll(resultSet,Product.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}

	@Override
	public Product findById(Serializable t) {
		String sql ="select * from product  where id=?";
		Product product=new Product();
		try {
			resultSet=this.executeQuery(sql, t);
			product= ResultsSetUtil.findById(resultSet, Product.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return product;
	}

	@Override
	public List<Product> finAllId(String... id) {
		StringBuffer sb=new StringBuffer("select * from product where id in(");
		Object [] objects=new Object[id.length];
		for (int i = 0; i < id.length; i++) {
			objects[i]=id[i];
			System.out.println("批量删除的Id"+id[i]);
			sb.append("?");
			if(i<id.length-1){
				sb.append(",");
			}
		}
		sb.append(")");
		System.out.println(""+sb.toString());
		try {
			 resultSet=this.executeQuery(sb.toString(), objects);
			 List<Product> list=ResultsSetUtil.findAll(resultSet, Product.class);
			if(list.size()>0){
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
