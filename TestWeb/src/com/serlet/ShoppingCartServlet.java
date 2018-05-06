package com.serlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;

import com.beans.Product;
import com.beans.ShoppingCart;
import com.beans.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serivce.impls.ProductServiceImpl;
import com.serivce.impls.ShoppingCartSerivceImpl;
import com.services.ProductService;
import com.services.ShoppingCartService;
import com.utils.Cart;
import com.utils.CartItem;
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet  extends HttpServlet{
	ShoppingCartService service=new ShoppingCartSerivceImpl();
	ProductService product=new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("进入====================》ShoppingCartServlet");
		String page=req.getParameter("method");
		System.out.println(req.getParameter("method"));
		switch (page) {
		case "select":
			select(req, resp);
			break;
		case "finId":
			System.out.println("进入====================》finId");
			finById(req, resp);
			break;
		case "def":
			System.out.println("进入====================》def");
			getProduct(resp);
			break;
		case "addcar":
			System.out.println("进入====================》addcar");
			addcart(req, resp);
			break;	
		default:
			
			break;
		}
	}
	private void addcart(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Gson gson = new Gson();
		User user=(User) req.getSession().getAttribute("user");
		System.out.println(user);
		ShoppingCart cart=new ShoppingCart();//购物车类
		if(user!=null){//判断是否有用户登陆添加购物车
			cart.setUserId(user.getUid());//用户Id
			cart.setProductId(Integer.parseInt(req.getParameter("id")));//商品Id
			cart.setProductNum(Integer.parseInt(req.getParameter("num")));//数量
			System.out.println("用户登陆添加购物车=====1");
			List<ShoppingCart> list=service.select(user.getUid());//第一次查找购物车
			if(list.size()!=0){//用户ID的购物车下有商品
				System.out.println("用户登陆添加购物车=====2");
				boolean flag=false;
				for (ShoppingCart shoppingCart : list) {//遍历商品
					System.out.println(shoppingCart);
					System.out.println("用户登陆添加购物车=====3");
					if(shoppingCart.getProductId().intValue()==cart.getProductId().intValue()){//其中某个商品与要存进购物车的商品Id一致
						flag=true;
						System.out.println("shoppingCart=====>"+shoppingCart.getProductId());
						System.out.println("cart=====>"+cart.getProductId());
						System.out.println("用户登陆添加购物车=====4");
						cart.setProductNum(shoppingCart.getProductNum()+cart.getProductNum());//将要存进购物车的数量与之前的数量相加
						if(service.updata(cart)!=-1){//调用service层方法，不为0即存进成功
							System.out.println("成功加入购物车=====>updata");
							
						}else{
							System.out.println("加入购物车失败");
							
						}
						resp.sendRedirect("page.jsp?id="+req.getParameter("id"));
					}
				}
				 if(!flag){
					if(service.add(cart)!=-1){//调用service层方法，不为0即存进成功
						System.out.println("成功加入购物车=======>add");
					}else{
						System.out.println("加入购物车失败");
					}
					resp.sendRedirect("page.jsp?id="+req.getParameter("id"));
				}
			}else{//用户ID的购物车下没有商品，直接存进数据库
				if(service.add(cart)!=-1){//调用service层方法，不为0即存进成功
					System.out.println("成功加入购物车");
				}else{
					System.out.println("加入购物车失败");
				}
				resp.sendRedirect("page.jsp?id="+req.getParameter("id"));
			}
		}else{//如果用户没登录，就存进cookie   
			/**
			 * 问题：商品数量没有叠加
			 */
			System.out.println("用户没登录=========>cookie");
			System.out.println("商品Id======>"+req.getParameter("id"));
			System.out.println("商品数量======>"+req.getParameter("num"));
			Cart carts1 =null;
			 //商品实体类
			Product	product1=product.findById(req.getParameter("id"));
			System.out.println("product1==========>"+product1);
			Cookie[] cookies = req.getCookies();//从请求中获取cookie
			for(int i=0;i<cookies.length;i++){
				System.out.println("cookie长度========>"+(i+1));
			    if("cart".equals(cookies[i].getName())){//判断是否存在购物车的商品
			    	System.out.println("cookie存在========>"+(i+1));
			        //System.out.println(cookies[i].getValue());//得到pete
			    	String value = cookies[i].getValue();//获取cookie的值
			    	carts1=	gson.fromJson(value, Cart.class);//将json数据反向生成对象
			    	
			    	carts1.addProduct(product1, Integer.parseInt(req.getParameter("num")));//将商品存进cart类
			    	String json = gson.toJson(carts1); //用Json返回给前端
					System.out.println("json=========>select:"+json);
					Cookie cookie1=new Cookie("cart", json);
					resp.addCookie(cookie1);
			    	break;
			    }else{//如果cookie不存在购物车
			    	System.out.println("cookie不存在========>"+(i+1));
			    	Cart carts = new Cart();
			    	carts.addProduct(product1,Integer.parseInt(req.getParameter("num")));
					String json = gson.toJson(carts); //用Json返回给前端
					System.out.println("json=========>select:"+json);
					Cookie cookie=new Cookie("cart", json);
					resp.addCookie(cookie);
			    }
			    
			}	
			resp.sendRedirect("page.jsp?id="+req.getParameter("id"));
		}
	}
	private void select(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("进入====================》select");
		User user1=(User) req.getSession().getAttribute("user");
		Gson gson = new Gson();
		if(user1!=null){//判断是否有用户登陆添加购物车
			System.out.println("用户已登陆========》");
			List<ShoppingCart> list2=service.select(user1.getUid());//查找购物车，返回给前段购物车页面(包含商品Id,及数量，用户Id)
			Product product1=null;//商品实体类
			List<CartItem> list3=new ArrayList<>();//存放购物车选项类
			CartItem cartItem=null;
			for (int i = 0; i < list2.size(); i++) {//遍历某用户的购物车
			cartItem=new CartItem();
			product1=product.findById(list2.get(i).getProductId().toString());//查找指定商品Id
			System.out.println("商品=====》"+product1);
			cartItem.setProduct(product1);//将值赋给购物车选项类
			System.out.println(cartItem.getProduct());
			cartItem.setNum(list2.get(i).getProductNum());
			System.out.println(cartItem.getNum());
			cartItem.setPrice(product1.getPrice());	
			System.out.println(cartItem.getPrice());
			list3.add(cartItem);//添加购物车选项类到集合中
			}
			String json = gson.toJson(list3); //用Json返回给前端
			System.out.println("json=========>select:"+json);
			PrintWriter writer = resp.getWriter();
			writer.print(json); // 返回数据给前台
			writer.close();
		}else{//用户没登陆，到cookie取值
			System.out.println("用户未登陆============>");
			Cookie[] cookies = req.getCookies();
			Cart carts1 = new Cart();
			List<CartItem> list4=new ArrayList<>();//存放购物车选项类
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("cart")){
					String value = cookies[i].getValue();//获取cookie的值
			    	carts1=	gson.fromJson(value, Cart.class);//将json数据反向生成对象
			    	Map<String, CartItem> map=carts1.getMap();
			    	for (Entry<String, CartItem> product : map.entrySet()) {
						CartItem cartItem = product.getValue();
						System.out.println("cartItem========>"+cartItem);
						list4.add(cartItem);
					}
				}
			}
			String json = gson.toJson(list4); //用Json返回给前端
			System.out.println("用户未登陆json=========>select:"+json);
			PrintWriter writer = resp.getWriter();
			writer.print(json); // 返回数据给前台
			writer.close();
		}
	}
	private void getProduct(HttpServletResponse resp) {
	
		
	}
	private void finById(HttpServletRequest req, HttpServletResponse resp) {
		
		List<ShoppingCart> list=service.select(req.getParameter("id"));
		Gson gson =new GsonBuilder()
        .create();
		String json = gson.toJson(list); 
		System.out.println(json);
		// 获取输出流对象
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			writer.print(json); // 返回数据给前台
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
