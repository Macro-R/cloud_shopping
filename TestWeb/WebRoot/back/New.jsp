<%@page import="com.beans.News"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            心健新闻
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="back/css/x-admin.css" media="all">
        
      <!--   <script type="text/javascript" src="js/jquery.js"></script> -->
        <script src="back/lib/layui/layui.js" charset="utf-8"></script>
        <script src="back/js/x-layui.js" charset="utf-8"></script>
   <!--  <script type="text/javascript" src="js/jquery.min.js"></script></head> -->
    <script type="text/javascript" src="back/js/jquery-1.12.4.js"></script>
    <body>
    <%
    	String nn=request.getParameter("me");
    	if("aa".equals(nn)){
    	System.out.println("进入News.jsp");
    		request.getRequestDispatcher("../NewsServlet?method=add").forward(request, response);
    	}
    %>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>网站信息管理</cite></a>
              <a><cite>心健新闻</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
        <form class="layui-form x-center" action="" style="width:500px">
                <div class="layui-form-pane">
                  <div class="layui-form-item">
                    <div class="layui-input-inline" style="width:400px">
                      <input type="text" name="username"  placeholder="搜索内容" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                </div> 
            </form>
            <xblock><button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button><button class="layui-btn" onclick="new_add('添加用户','back/new_add.jsp','600','500')"><i class="layui-icon">&#xe608;</i>添加</button><span class="x-right" style="line-height:25px">共有数据：${sessionScope.Total} 条</span></xblock>
             <form id="form2" action="NewsServlet?method=delAll" method="post">
            <table class="layui-table">
                <thead>
                    <tr>
                        <th width="2%"><input type='checkbox' name='checkboxAll' onclick="swapCheck()">全选</th>
                        <th width="5%">序号</th>
                        <th width="8%">模块</th>
                        <th width="5%">标题</th>
                        <th width="8%">图片</th>
                        <th width="9%">发表时间</th>
                        <th width="44%">内容</th>
                        <th width="8%">显示状态</th>
                        <th width="9%">操作</th>
                    </tr>
                </thead>
             
                <tbody id="list-content">
                
                </tbody>
            </table>
              </form>
            <div id="page"></div>
        </div>
        
            <br /><br /><br />
        <script>

/*  动态获取数据，并拼接      */  
load(0);  //默认初始化,pageNum为0
var data;
function  load(pageNum) {
       $.ajax({
           url: "page",  //需要提交的服务器地址
           type: "post",  //请求的方式
           data: {"pageNum": pageNum},  //传递给服务器的参数
           success: function (data) {  //回调函数
             data=$.parseJSON(data);//从数据库获得的json对象，已经包含查询回来的数据
             
                                        
              /* 用layui实现分页 */
 layui.use(['laydate','element','laypage','layer'], function(){
              $ = layui.jquery;//jquery
              laydate = layui.laydate;//日期插件
              lement = layui.element();//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              
			  //以上模块根据需要引入
              laypage({
                cont: 'page'
                ,pages: data.pages
                ,curr:data.pageNum
                ,skip:true
                ,prev: '<em><</em>'
                ,next: '<em>></em>'
                ,jump: function(obj, first){ //触发分页后的回调
                  if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                      $("#list-content").text('');//先清空原先内容
                      console.log(obj.curr);
                      load(obj.curr);
                  }
                }
              }); 
                layer.ready(function(){ //为了layer.ext.js加载完毕再执行
                  layer.photos({
                    photos: '#list-content'
                    //,shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
                  });
                }); 
              
            });
               //清空数据
               $("#list-content").html('');
               //追加数据  data.list需要遍历的集合  list必须是pageInfo中的属性名
 $.each(data.list, function (i, news) {
 //在表格中拼接                      
 $("#list-content").append("<tr><td><input type='checkbox' id='checkbox' value='"+news.id+"' name='checkbox'></td>"
+ "<td>"
+ news.id
+ "</td>"
+ "<td>"+news.module+"</td>"
+ "<td>"
+ news.title
+ "</td>"
+ "<td align='left'><img src='"+news.img+"' alt='' width='56'height='56' ></td>"
+ "<td>"
+ news.createTime
+ "</td>"
+ "<td>"
+ news.content
+ "</td>"
+ "<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini'> 已显示 </span></td>"
+ "<td class='td-manage'><a style='text-decoration:none'onclick='banner_stop(this,'10001')' "
+"href='javascript:; 'title='不显示'> <i class='layui-icon'>&#xe601;</i> </a> "
+"<a title='修改'href='javascript:; 'onclick='new_edit(&apos;修改&apos;,&apos;NewsServlet?method=finId&id="+news.id
+"&apos;,4,&apos;&apos;,510)' class='ml-5' style='text-decoration:none'> <i class='layui-icon'>"
+"&#xe642;</i> </a> <a title='删除' href='javascript:;' onclick='banner_del(this,"+news.id+")' style='text-decoration:none'>"
+" <i class='layui-icon'>&#xe640;</i> </a></td>");
               });
           }
       });
   };
        
    
         /*    各种弹出框 */
            //批量删除提交
             function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                    $("#form2").submit(); 
                });
                 
             }
             /*添加*/
            function new_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
             /*停用*/
            function banner_stop(obj,id){
                layer.confirm('确认不显示吗？',function(index){
                    //发异步把用户状态进行更改
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="banner_start(this,id)" href="javascript:;" title="显示"><i class="layui-icon">&#xe62f;</i></a>');
                    $(obj).parents("tr").find(".td-status").jsp('<span class="layui-btn layui-btn-disabled layui-btn-mini">不显示</span>');
                    $(obj).remove();
                    layer.msg('不显示!',{icon: 5,time:1000});
                });
            }
            /*启用*/
            function banner_start(obj,id){
                layer.confirm('确认要显示吗？',function(index){
                    //发异步把用户状态进行更改
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="banner_stop(this,id)" href="javascript:;" title="不显示"><i class="layui-icon">&#xe601;</i></a>');
                    $(obj).parents("tr").find(".td-status").jsp('<span class="layui-btn layui-btn-normal layui-btn-mini">已显示</span>');
                    $(obj).remove();
                    layer.msg('已显示!',{icon: 6,time:1000});
                });
            }
            // 编辑
            function new_edit (title,url,id,w,h) {
                x_admin_show(title,url,w,h); 
            }
            /*删除*/
            function banner_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                    window.location="NewsServlet?method=del&id="+id;
                });
            }
            </script>
              <script>
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        </script>
        <script type="text/javascript">  
        //checkbox 全选/取消全选  
        var isCheckAll = false;  
        function swapCheck() {  
            if (isCheckAll) {  
                $("input[type='checkbox']").each(function() {  
                    this.checked = false;  
                });  
                isCheckAll = false;  
            } else {  
                $("input[type='checkbox']").each(function() {  
                    this.checked = true;  
                });  
                isCheckAll = true;  
            }  
        }  
        
    </script>  
        
    </body>
</html>
