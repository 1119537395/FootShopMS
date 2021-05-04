<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>服务人员管理页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${Path}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${Path}/static/css/public.css" media="all"/>
</head>
<body style="margin: 10px">

<!-- 搜索条件开始 -->
<blockquote class="layui-elem-quote">
    <i class="layui-icon layui-red">&#xe615;</i>
    查询条件
</blockquote>
<form class="layui-form layui-form-pane" method="post" id="searchFrom">

    <div class="layui-form-item">



        <div class="layui-inline">
            <label class="layui-form-label">工号</label>
            <div class="layui-input-inline">
                <input type="text" name="staffNumber" autocomplete="off" class="layui-input" placeholder="请输入查询的工号">
            </div>
        </div>


        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="staffName" autocomplete="off" class="layui-input" placeholder="请输入查询的姓名">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="staffPhone" autocomplete="off" class="layui-input" placeholder="请输入查询的手机号">
            </div>
        </div>





        <div class="layui-inline">
            <div class="layui-input-label">
                <button type="button" class="layui-btn layui-icon layui-icon-search" id="doSearch">&nbsp;查询</button>
                <button type="reset" class="layui-btn layui-icon layui-btn-danger layui-icon-refresh">&nbsp;重置</button>
            </div>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->


<!-- 数据表格开始 -->

<!-- 表格内嵌效果开始 -->
<script type="text/html" id="staffWorkState">
    <input type="checkbox" name="Unfold1" value="{{d.staffWorkState}}" lay-skin="switch" lay-text="服务中|休息中" {{
           d.staffWorkState==1?'checked':''}}>
</script>
<script type="text/html" id="staffServiceState">
    <input type="checkbox" name="Unfold2" value="{{d.staffServiceState}}" lay-skin="switch" lay-text="在职|离职" {{
           d.staffServiceState==1?'checked':''}}>
</script>
<!-- 表格内嵌效果结束 -->

<table class="layui-hide" id="staffTable" lay-filter="staffTable"></table>

<!-- 数据表格上方工具栏按钮 -->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-containe">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="addStaffDataBtn">添加</button>
    </div>
</script>

<!-- 数据表格内部操作按钮 -->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">注销</a>
</script>


<!-- 数据表格结束 -->


<!-- 添加和修改的弹出层开始 -->
<div style="display: none" id="addAndEditModel">
    <form class="layui-form layui-form-pane" action="" lay-filter="addAndEditStaffFrame" id="addAndEditStaffFrame"
          style="margin: 20px">
        <blockquote class="layui-elem-quote">
            <i class="layui-icon layui-blue">&#xe609;</i>
            员工信息管理
        </blockquote>

        <div class="layui-form-item">
            <label class="layui-form-label">工号</label>
            <div class="layui-input-block">
                <input type="text" name="staffNumber" autocomplete="off" lay-verify="required|number|staffNumber" class="layui-input" placeholder="请输入员工工号">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="staffId" id="staffId" style="display: none">
                <input type="text" name="staffName" lay-verify="required" autocomplete="off" class="layui-input"
                       placeholder="请输入员工姓名">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <select name="staffSex">
                    <option value="">请选择员工性别</option>
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">号码</label>
            <div class="layui-input-block">
                <input type="text" name="staffPhone" autocomplete="off" lay-verify="required|number" class="layui-input" placeholder="请输入手机号码">
            </div>
        </div>


        <div class="layui-form-item" align="center">
            <div class="layui-input-label">
                <button type="button" class="layui-btn layui-icon layui-icon-ok-circle  layui-btn-radius" lay-submit=""
                        lay-filter="toEditAndAddSubmit" id="toEditAndAddSubmit">&nbsp;提交
                </button>
                <button type="reset"
                        class="layui-btn layui-icon layui-btn-danger layui-icon-refresh   layui-btn-radius">&nbsp;重置
                </button>
            </div>
        </div>

    </form>
</div>
<!-- 添加和修改的弹出层结束 -->


<script src="${Path}/static/layui/layui.js" charset="utf-8"></script>
<script>
    var tableIns;
    layui.use(['table', 'layer', 'form', 'jquery', 'laydate'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var laydate = layui.laydate;
        // 管理员类型权限控制
        let typeId = ${user.userType};


        // 渲染表格数据
        tableIns = table.render({
            elem: '#staffTable'
            , url: '${Path}/staff/loadStaffInfo'
            , toolbar: '#toolbarDemo'
            , height: 'full-190'
            , cellMinWidth: 100
            , page: true
            , text: {
                none: "暂无相关数据"
            }
            , title: '员工信息表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'staffId', title: 'ID', fixed: "left", align: "center"}
                , {field: 'staffNumber', title: '工号', align: "center"}
                , {field: 'staffName', title: '姓名', align: "center"}
                , {
                    field: 'staffSex', title: '性别', align: "center", templet: function (data) {
                        return data.staffSex == '1' ? '<font color="#87cefa">男</font>' : '<font color="#ffc0cb">女</font>';
                    }
                }
                , {field: 'staffPhone', title: '号码', align: "center"}
                , {field: 'staffWorkState', title: '工作状态', align: "center",templet: '#staffWorkState'}
                , {field: 'staffServiceState', title: '在职状态', align: "center",templet: '#staffServiceState'}
                , {field: 'staffCreateTime', title: '入职时间', align: "center"}
                , {field: 'createName', title: '创建人', align: "center"}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: "center"}
            ]]
        });

        //给查询按钮添加单击事件
        $("#doSearch").click(function () {
            //获取表单提交的数据序列
            var parameter = $("#searchFrom").serialize();
            tableIns.reload({
                url: "${Path}/staff/loadStaffInfo?" + parameter,
                page: {curr: 1}
            });
        });

        //监听头工具栏添加按钮和删除按钮事件
        table.on('toolbar(staffTable)', function (obj) {
            switch (obj.event) {
                case 'addStaffDataBtn':
                    if (typeId==1){
                        layer.msg("没有权限访问！",{
                            icon:5,
                            anim:6
                        });
                        return;
                    }
                    OpenAddMenuModel();
                    break;
            }
        });

        //监听行工具注销和修改和分配按钮事件
        table.on('tool(staffTable)', function (obj) {
            var staffData = obj.data;  //获取到单击行的数据
            if (obj.event === 'delete') {
                if (typeId==1){
                    layer.msg("没有权限访问！",{
                        icon:5,
                        anim:6
                    });
                    return;
                }
                layer.confirm('确定注销【' + staffData.staffName + '】的信息吗？', {icon: 3, title: '提示:'}, function () {
                    $.post("${Path}/staff/deleteStaffInfo?", {staffId: staffData.staffId}, function (returnValue) {
                        if (returnValue.code == 200) {
                            layer.msg(returnValue.msg, {
                                icon: 6
                            });
                            //刷新表格
                            tableIns.reload();
                        } else {
                            layer.msg(returnValue.msg, {
                                icon: 5
                            });
                        }
                    });
                });
            } else if (obj.event === 'edit') {
                if (typeId==1){
                    layer.msg("没有权限访问！",{
                        icon:5,
                        anim:6
                    });
                    return;
                }
                OpenUpdateModel(staffData);
            }
        });


        //表单验证
        form.verify({
            staffNumber: function (value) {
                var staffId = $("#staffId").val();
                var formData = new FormData();
                formData.append("staffNumber", value);
                formData.append("staffId", staffId);
                var message = '';
                $.ajax({
                    url: "${Path}/staff/checkStaffNumber",
                    type: 'post',
                    data: formData,
                    async: false,
                    cache: false,
                    processData: false,
                    contentType: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            message = "输入的工号已经存在，请重新输入";
                        }
                    }
                });
                if (message !== '') {
                    return message;
                }
            }
        });


        //打开添加员工信息模态框
        var maxIndex;
        var url;

        function OpenAddMenuModel() {
            maxIndex = layer.open({
                type: 1,
                title: "添加员工信息",
                content: $('#addAndEditModel'),
                area: ["700px", "430px"],
                anim: 5,
                skin: "layui-layer-molv",
                closeBtn: 2,
                success: function (index) {
                    //清空表单
                    $("#addAndEditStaffFrame")[0].reset();
                    url = "${Path}/staff/addStaffInfo";
                }
            });
        }

        //打开修改员工信息模态框
        function OpenUpdateModel(data) {
            maxIndex = layer.open({
                type: 1,
                title: "修改员工信息",
                content: $('#addAndEditModel'),
                area: ["700px", "430px"],
                anim: 5,                 //动画效果
                skin: "layui-layer-molv", //皮肤
                closeBtn: 2,
                success: function (index) {
                    //给表单复制
                    form.val("addAndEditStaffFrame", data);
                    $("#staffId").val(data.staffId);
                    url = "${Path}/staff/updateStaffInfo";
                }
            });
        }

        //保存添加或修改的员工数据
        form.on('submit(toEditAndAddSubmit)', function (data) {
            //序列化表单数据
            var params = $("#addAndEditStaffFrame").serialize();
            $.post(url, params, function (obj) {
                //弹出成功或失败的提示信息
                layer.msg(obj.msg, {
                    icon: 6
                });
                //关闭弹出层
                layer.close(maxIndex);
                //刷新表格
                tableIns.reload();
            });
        });

    });


</script>

</body>
</html>