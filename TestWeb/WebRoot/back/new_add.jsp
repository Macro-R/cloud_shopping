<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
           心健新闻-添加
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="./css/x-admin.css" media="all">
        <script type="text/javascript" src="laydate/laydate.js"></script>
         <script type="text/javascript" src="js/jquery.js"></script>
         <script src="./lib/layui/layui.js" charset="utf-8"></script>
        <script src="./js/x-layui.js" charset="utf-8"></script>
        <style type="text/css">
        	.site-demo-upbar{
        		border:1px black solid;
        	}
        	
        	.layui-input{
        	height:50px;
        }
        *{margin:0;padding:0;list-style:none;}
		.box{width:970px; padding:10px 20px; background-color:#fff; margin:10px auto;}
		.demo2{margin:25px 0;}
		.layinput{height: 22px;line-height: 22px;width: 150px;margin: 0;}
        </style>   
    </head>
    
    <body>
        <div class="x-body"><!-- ../NewsServlet?method=add -->
            <form class="layui-form" id="form1" action="../NewsServlet?method=add" method="post" enctype="multipart/form-data">
           <!--   <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>序号
              </label>
                    <div class="layui-input-inline">
                        <input type="text" id="link" name="id" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div> -->
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>模块
                    </label>
                    <div class="layui-input-inline">
                        <select lay-verify="required" name="module">
                                <option>
                                </option>
                                <option label="模块">
                                    <option value="心健新闻">心健新闻</option>
                                    <option value="媒体报道">媒体报道</option>
                                    <option value="心理新闻">心理新闻</option>
                                </option>
                            </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>标题
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="link" name="title" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>发表时间
                    </label>
                    <div class="layui-input-inline">
                        <!-- <input type="text" id="link" name="link" required lay-verify="required"
                        autocomplete="off" class="layui-input"> -->
                        <input placeholder="请输入日期"  id="link" name="createTime" required lay-verify="required"  autocomplete="off" class="layui-input laydate-icon" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" >
                    </div>
                </div>
            
                <div class="layui-form-item">
                    <label for="link" class="layui-form-label">
                        <span class="x-red">*</span>轮播图
                    </label>
                    <div class="layui-input-inline">
                      <div class="site-demo-upbar">
                        <input type="file" name="img" id="test"  onchange="showPreview(this)">
                      </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">图片
                    </label>
                    <img id="portrait" width="400" src="">
                </div>
                <div class="layui-form-item">
                    <label for="desc" class="layui-form-label">
                        <span class="x-red">*</span>文字信息
                    </label>
                    <div class="layui-input-inline">
                        <textarea id="L_content" name="content" 
                        placeholder="简介" class="layui-textarea fly-editor" style="height: 260px;">简介</textarea>
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span>
                    </div>
                </div>
            </form>
             <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <button  class="layui-btn" onclick="banner_del(this,'1')" >增加
                    </button>
                </div>
        </div>

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
	</script>
 
<script type="text/javascript">
  function banner_del(obj,id){
   
                layer.confirm('确认要添加吗？',function(index){
                    //发异步删除数据
                    /* $(obj).parents("tr").remove(); */
                    layer.msg('已添加!',{icon:1,time:2000});
                    /*window.location="NewsServlet?method=del&id="+id; */
                     // 获得frame索引
                     var index = parent.layer.getFrameIndex(window.name);	
	 					parent.layer.close(index);
                    //关闭当前frame
                 }); 
	 			$("#form1").submit();
             }
       
!function(){
laydate.skin('molv');//切换皮肤，请查看skin	s下面皮肤库
laydate({elem: '#demo'});//绑定元素
}();
    
    layui.use(['form','layer','upload'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;  
    }); 
</script>
       
</body>

</html>