<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.beans.News" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
           心健新闻-编辑
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="back/css/x-admin.css" media="all">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="back/laydate/laydate.js"></script>
         <script src="back/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="back/js/x-layui.js" charset="utf-8">
        </script>
        
        <style type="text/css">
         .site-demo-upbar{
        		border:1px black solid;
        	}
        </style>
       <style type="text/css">
        .layui-input{
        	height:50px;
        }
        *{margin:0;padding:0;list-style:none;}
		.box{width:970px; padding:10px 20px; background-color:#fff; margin:10px auto;}
		.box a{padding-right:20px;}
		.demo2{margin:25px 0;}
		.layinput{height: 22px;line-height: 22px;width: 150px;margin: 0;}
		.laydate-icon{
			
		}
        </style>   
  
    </head>
    <body>
        <div class="x-body">
            <form class="layui-form" action="NewsServlet?method=update" method="post" enctype="multipart/form-data">
            <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>序号
              </label>
                    <div class="layui-input-inline">
                        <input type="text" id="link" name="id" value="${requestScope.news.id }"  required lay-verify="required"
                        autocomplete="off" class="layui-input" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>模块
                    </label>
                    <div class="layui-input-inline">
                        <select lay-verify="required" name="module" >
                                <option>
                                </option>
                                <optgroup label="模块" >
                                
                                		<c:if test="${ requestScope.news.module eq '心健新闻'}">
                                		<option value="心健新闻" selected>心健新闻</option>
                                		</c:if>
                                		
                                		<c:if test="${ requestScope.news.module ne '心健新闻'}">
                                		<option value="心健新闻">心健新闻</option>
                                		</c:if>
                                		
                                		<c:if test="${ requestScope.news.module eq '媒体报道'}">
                                		<option value="媒体报道" selected>媒体报道</option>
                                		</c:if>
                                		
                                		<c:if test="${ requestScope.news.module ne '媒体报道'}">
                                		<option value="媒体报道">媒体报道</option>
                                		</c:if>
                                		
                                		<c:if test="${ requestScope.news.module eq '心理新闻'}">
                                		<option value="心理新闻" selected>心理新闻</option>
                                		</c:if>
                                		
                                		<c:if test="${ requestScope.news.module ne '心理新闻'}">
                                		<option value="心理新闻">心理新闻</option>
                                		</c:if>

                                </optgroup>
                            </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>标题
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="link" name="title"  required lay-verify="required"
                        autocomplete="off" class="layui-input" value="${ requestScope.news.title}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>发表时间
                    </label>
                    <div class="layui-input-inline">
                        <%-- <input type="text" id="link" name="name="createTime" required lay-verify="required"
                        autocomplete="off" class="layui-input" value="${ requestScope.news.createTime}"> --%>
                        
                        <input placeholder="请输入日期"  value="${ requestScope.news.createTime}" class="layui-input laydate-icon" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" >
                    </div>
                </div>
            
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                    <span class="x-red">*</span>图片</label>
                    <div class="layui-input-inline">
                      <div class="site-demo-upbar">
                        <input type="file" name="img" value=""  onchange="showPreview(this)"><!-- class="layui-upload-file" id="test" -->
                      </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">图片
                    </label>
                    <c:if test="${(not empty requestScope.news.img)&&(requestScope.news.img ne '')  }">
                    	<img id="portrait" width="400" src="upload/${requestScope.news.img}">
                    </c:if>
                     <c:if test="${(empty requestScope.news.img)||requestScope.news.img eq ''}">
                     	<img id="portrait" width="400" src="">
                     </c:if>
                </div>
                <div class="layui-form-item">
                    <label for="desc" class="layui-form-label">
                        <span class="x-red">*</span>文字信息
                    </label>
                    <div class="layui-input-inline">
                        <textarea id="L_content" name="content" 
                        placeholder="简介" class="layui-textarea fly-editor" style="height: 260px;"><c:out value="${requestScope.news.content }"></c:out></textarea>
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span>
                    </div>
                </div>
                 </form>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <button  class="layui-btn" onclick="banner_del(this,'1')" >
                             <!-- lay-filter="*" lay-submit="updata" onclick="click()"  class="layui-btn"    -->
                        完成
                    </button>
                    <!-- <a href="" class="layui-btn" ></a> -->
                </div>
           
        </div>

        <script type="text/javascript">

        function banner_del(obj,id){
                layer.confirm('确认要修改吗？',function(index){
                    //发异步删除数据
                   /*  $(obj).parents("tr").remove(); */
                    layer.msg('已修改!',{icon:1,time:2000});
                     
                    /* window.location="NewsServlet?method=del&id="+id; */
                     // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                });
               $(".layui-form").submit();
            } 
!function(){
laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo'});//绑定元素
}();
    </script>  
        
        <script type="text/javascript">
		function showPreview(source) {

			var file = source.files[0];

			if (window.FileReader) {

				var fr = new FileReader();

				console.log(fr);

				var portrait = document.getElementById('portrait');

				fr.onloadend = function(e) {

					portrait.src = e.target.result;

				};

				fr.readAsDataURL(file);

				portrait.style.display = 'block';

			}

		}
            layui.use(['form','layer','upload'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
            });
        </script>
    </body>

</html>