package com.serlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.News;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serivce.impls.NewsServiceImpl;
import com.services.NewsService;
import com.utils.PageInfo;
@WebServlet("/page")
public class TestServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//新闻serviceImpl层实例
		NewsService service =new NewsServiceImpl();
		//获取PageInfo对象，对象的List结果集从dao层的findAlls获取
		PageInfo<News> news=service.findAlls(Integer.parseInt(req.getParameter("pageNum")), 5);
		//获取总记录数
		news.setTotal(service.getTotalCount());
		System.out.println("pageNum=====>" + req.getParameter("pageNum"));
		//设置当前页码数
		news.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		System.out.println("总记录数===》" + news.getTotal());
		List<News> list= news.getList();
		req.getSession().setAttribute("Total", news.getTotal()+"");
		Iterator<News> iterator = list.iterator();
		while (iterator.hasNext()) {
			News new2=(News)iterator.next();
			System.out.println(new2);
			System.out.println("img===============>"+new2.getImg());
			if(new2.getImg()!=null){
				new2.setImg(new2.getImg().substring(new2.getImg().indexOf("upload")));
				System.out.println("处理后img===============>"+new2.getImg());
			}
		}
		Gson gson =new GsonBuilder()
        .setDateFormat("yyyy-MM-dd hh:mm:ss")
        .create();
		String json = gson.toJson(news); 
		System.out.println(json);
		// 获取输出流对象
		PrintWriter writer = resp.getWriter();
		writer.print(json); // 返回数据给前台
		writer.close();
	}

}
