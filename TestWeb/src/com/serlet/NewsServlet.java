package com.serlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Delayed;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.daos.NewsDao;
import com.dao.impls.NewsDaoImpl;
import com.serivce.impls.NewsServiceImpl;
import com.services.NewsService;
import com.beans.News;
@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet{
private NewsService newsService=new NewsServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*req.setCharacterEncoding("utf-8");*/
		String method=req.getParameter("method");
		System.out.println(method+"");
		switch (method) {
		case "add":
			add(req,resp);
			break;
		case "del":
			System.out.println("进入del");
			del(req,resp);
			break;
		case "finAll":
			finAll(req,resp);
			break;
		case "update":
			updatd(req,resp);
			break;
		}
	}

	private void del(HttpServletRequest req, HttpServletResponse resp) {
		if(newsService.delete(req.getParameter("id"))>0){
			System.out.println("删除成功");
			
		}else {
			System.out.println("删除失败");
		}
		try {
			resp.sendRedirect("NewsServlet?method=finAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void finAll(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("进入finall");
		List<News> list =new ArrayList<>();
		list=newsService.select();
		req.setAttribute("list",list);
		try {
			req.getRequestDispatcher("/back/New.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updatd(HttpServletRequest req, HttpServletResponse resp) {

			int f = newsService.updata(storage(req,"update"));//sevriceImpl层调用dao层添加新闻的方法
			if (f>0) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失败");

			}
			try {
				System.out.println("11111");
				resp.sendRedirect("NewsServlet?method=finAll");
				System.out.println("11111");
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		
		int f = newsService.add(storage(req,"add"));//sevriceImpl层调用dao层添加新闻的方法
		if (f>0) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");

		}
	}

	private News storage(HttpServletRequest req,String num) {
		News news=new News();
		String id="";
		String img="";
		System.out.println("add临时文件存放的位置：====》"
				+ System.getProperty("java.io.tmpdir"));
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean flag = upload.isMultipartContent(req);
		if(flag){
			try {
				List<FileItem> items = upload.parseRequest(req);
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) {
					FileItem fileItem=it.next();
					if(fileItem.isFormField()){
						
						
						String fieldName = fileItem.getFieldName();
						System.out.println(fieldName);
						if(fieldName.equals("id")){
							id=fileItem.getString("UTF-8");
						}
						switch (fieldName) {
						case "id":
							System.out.println(fileItem.getString("UTF-8"));
							news.setId(Integer.parseInt(fileItem.getString("UTF-8")));
							break;
						case "title":
							System.out.println(fileItem.getString("UTF-8"));
							news.setTitle(fileItem.getString("UTF-8"));				
							break;
						case "content":
							System.out.println(fileItem.getString("UTF-8"));
							news.setContent(fileItem.getString("UTF-8"));
							break;
						case "module":
							System.out.println(fileItem.getString("UTF-8"));
							news.setModlue(fileItem.getString("UTF-8"));
							break;
						case "createTime":
							System.out.println(fileItem.getString("UTF-8"));
							news.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.parse(fileItem.getString("UTF-8")));
							break;
						}
					}else {// 证明是文件的元素
							String path=newsService.filePath(id);
							if(num.equals("update")&&path!=null){
							System.out.println(path+"11111111111111111"+id);
							File file2 = new File(path);
							if (file2.exists()) {
								if (file2.delete()) {
									System.out.println("删除成功");
								}else {
									System.out.println("删除失败");
								}
							}
						}
						String uploadPath = req.getSession()
								.getServletContext().getRealPath("upload");
						// 创建upload文件夹
						File file = new File(uploadPath);
						if (!file.exists()) {
							file.mkdirs();
						}
							
						String fileName = fileItem.getName();// 获取上传文件的名称
						fileName = new String(fileName.getBytes());// 解决中文乱码
						System.out.println(fileName);
						if (!"".equals(fileName) && null != fileName) {
							
							File saveFile = new File(uploadPath, fileName);
							System.out.println(uploadPath);
							System.out.println(fileName);
							fileItem.write(saveFile);
							news.setImg(uploadPath + "\\" + fileName);// System.currentTimeMillis()
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return news;
	}
	
}
