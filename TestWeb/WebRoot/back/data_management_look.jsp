<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            数据管理-查看
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="./css/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                        编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" name="username" required lay-verify="required"
                        autocomplete="off" value="1" disabled="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                       处理人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" name="username" required lay-verify="required" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                        处理类型
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" name="username" required lay-verify="required"
                        autocomplete="off" value="上传图片"  class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label for="content" class="layui-form-label">
                        具体内容
                    </label>
                    <div class="layui-input-block">
                         <textarea disabled="" id="content" name="content" autocomplete="off"
                        class="layui-textarea" style="height: 80px;">内容就是暂时没有内容......</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                        处理时间
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" name="username" required lay-verify="required"
                        autocomplete="off" value="2017-01-10 16:33:45"  class="layui-input">
                    </div>
                </div>
                 <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                        处理状态
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" name="username" required lay-verify="required"
                        autocomplete="off" value="已审核"  class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn">
                        返回
                    </button>
                </div>
                
            </form>
        </div>
        <script src="./lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="./js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
            
              //自定义验证规则
              // form.verify({
              //   nikename: function(value){
              //     if(value.length < 5){
              //       return '昵称至少得5个字符啊';
              //     }
              //   }
              //   ,pass: [/(.+){6,12}$/, '密码必须6到12位']
              //   ,repass: function(value){
              //       if($('#L_pass').val()!=$('#L_repass').val()){
              //           return '两次密码不一致';
              //       }
              //   }
              // });
              //监听提交
              form.on('submit(save)', function(data){
                console.log(data);
                //发异步，把数据提交给php
                layer.alert("", {icon: 6},function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                });
                return false;
              });
              
              
            });
        </script>
        <script>
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        </script>
    </body>

</html>