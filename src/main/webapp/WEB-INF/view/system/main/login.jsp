<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录界面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${Path}/static/pedicure.png">
    <link rel="stylesheet" href="${Path}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${Path}/static/css/public.css" media="all" />
</head>
<body class="loginBody">
<form class="layui-form" method="post" id="LoginFormId" action="${Path}/login/userLogin">
    <div class="login_face"><img id="userImg" src="${Path}/static/images/logo.jpg" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="userAccount">账户名</label>
        <input type="text" placeholder="请输入账户名" id="userAccount" autocomplete="off"  name="userAccount" class="layui-input">
    </div>
    <div class="layui-form-item input-item">
        <%-- lay-verify="required" --%>
        <label for="userPassword">密码</label>
        <input type="password" placeholder="请输入密码" id="userPassword" autocomplete="off" name="userPassword" class="layui-input" >
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" name="code" autocomplete="off" id="code" class="layui-input">
        <%-- 获取到后台生成的验证码 --%>
        <img src="${Path}/login/getCaptcha" onclick="this.src=this.src+'?'">
    </div>

    <div class="layui-form-item" >
        <button class="layui-btn layui-block"  lay-filter="login" lay-submit="">登录</button>
    </div>

    <!-- 显示登录失败的提示信息 -->
    <div class="layui-form-item layui-row" style="text-align: center;color: red;" >
        ${LOGIN_ERROR}

        ${CAPTCHA_ERROR}
    </div>
</form>
<script type="text/javascript" src="${Path}/static/layui/layui.js"></script>
<script type="text/javascript" src="${Path}/static/js/cache.js"></script>
<script type="text/javascript">


    layui.use(['form','layer','jquery'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;


        //登录按钮
        form.on("submit(login)",function(data){
            $(this).text(" 正在登录...").attr("disabled","disabled").addClass("layui-disabled");
            // 加载圈
            layer.load(0, {shade: false});
            // 提交延迟
            setTimeout(function(){
               $("#LoginFormId").submit();
            },200);
            return false;
        });


        //表单输入效果
        $(".loginBody .input-item").click(function(e){
            e.stopPropagation();
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
        })
        $(".loginBody .layui-form-item .layui-input").focus(function(){
            $(this).parent().addClass("layui-input-focus");
        })
        $(".loginBody .layui-form-item .layui-input").blur(function(){
            $(this).parent().removeClass("layui-input-focus");
            if($(this).val() != ''){
                $(this).parent().addClass("layui-input-active");
            }else{
                $(this).parent().removeClass("layui-input-active");
            }
        });
    });
</script>
</body>
</html>
