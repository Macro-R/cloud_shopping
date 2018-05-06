package com.serlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Product;
import com.beans.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serivce.impls.ProductServiceImpl;
import com.services.ProductService;
@WebServlet("/ProductServlet")
/**
 * 
 *商品Servlet
 * @author Macro
 *
 */
public class ProductServlet extends HttpServlet{
	ProductService service=new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入====================》ProductServlet");
		String page=req.getParameter("method");
		
		switch (page) {
		case "finId":
			System.out.println("进入====================》finId");
			finById(req, resp);
			break;
		case "def":
			System.out.println("进入====================》def");
			getProduct(resp);
			break;
		case "addcar":
			System.out.println("进入====================》def");
			User user=(User) req.getSession().getAttribute("user");
			if(user!=null){
				
				/*service.add(t)*/
			}
			break;	
		default:
			
			break;
		}
		
	}

	private void finById(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("id===============>"+req.getParameter("id"));
		Product product=service.findById(req.getParameter("id"));
		Gson gson =new GsonBuilder()
		.create();
		String json = gson.toJson(product); 
		System.out.println("json:"+json);
		PrintWriter writer = resp.getWriter();
		writer.print(json); // 返回数据给前台
		writer.close();
	}

	private void getProduct(HttpServletResponse resp) throws IOException {
		List<Product> list=service.select();
		Gson gson =new GsonBuilder()
        .create();
		String json = gson.toJson(list); 
		System.out.println("json:"+json);
		PrintWriter writer = resp.getWriter();
		writer.print(json); // 返回数据给前台
		writer.close();
	}

}
