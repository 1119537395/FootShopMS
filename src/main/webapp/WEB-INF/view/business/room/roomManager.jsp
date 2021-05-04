<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>房间信息管理页面</title>
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
            <label class="layui-form-label">房间号</label>
            <div class="layui-input-inline">
                <input type="text" name="roomNumber" autocomplete="off" class="layui-input" placeholder="请输入查询的房间号">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-inline">
                <input type="text" name="roomName" autocomplete="off" class="layui-input" placeholder="请输入查询的房间名称">
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
<script type="text/html" id="roomState">
    <input type="checkbox" name="Unfold1" value="{{d.roomState}}" lay-skin="switch" lay-text="空房|使用中" {{
           d.roomState==1?'checked':''}}>
</script>
<!-- 表格内嵌效果结束 -->

<table class="layui-hide" id="roomTable" lay-filter="roomTable"></table>

<!-- 数据表格上方工具栏按钮 -->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-containe">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="addRoomDataBtn">添加</button>
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
    <form class="layui-form layui-form-pane" action="" lay-filter="addAndEditRoomFrame" id="addAndEditRoomFrame"
          style="margin: 20px">
        <blockquote class="layui-elem-quote">
            <i class="layui-icon layui-blue">&#xe609;</i>
            房间信息管理
        </blockquote>

        <div class="layui-form-item">
            <label class="layui-form-label">房间号</label>
            <div class="layui-input-block">
                <input type="text" name="id" id="roomId" style="display: none">
                <input type="text" name="roomNumber" lay-verify="required|number|roomNumber" autocomplete="off" class="layui-input"
                       placeholder="请输入房间号">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">房间名称</label>
            <div class="layui-input-block">
                <input type="text" name="roomName" autocomplete="off" lay-verify="required" class="layui-input" placeholder="请输入房间名称">
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
            elem: '#roomTable'
            , url: '${Path}/room/loadRoomInfo'
            , toolbar: '#toolbarDemo'
            , height: 'full-190'
            , cellMinWidth: 100
            , page: true
            , text: {
                none: "暂无相关数据"
            }
            , title: '房间信息表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', fixed: "left", align: "center"}
                , {field: 'roomNumber', title: '房间号', align: "center"}
                , {field: 'roomName', title: '房间名称', align: "center"}
                , {field: 'roomState', title: '房间状态', align: "center",templet: '#roomState'}
                , {field: 'createTime', title: '创建时间', align: "center"}
                , {field: 'createName', title: '创建人', align: "center"}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: "center"}
            ]]
        });

        //给查询按钮添加单击事件
        $("#doSearch").click(function () {
            //获取表单提交的数据序列
            var parameter = $("#searchFrom").serialize();
            tableIns.reload({
                url: "${Path}/room/loadRoomInfo?" + parameter,
                page: {curr: 1}
            });
        });

        //监听头工具栏添加按钮和删除按钮事件
        table.on('toolbar(roomTable)', function (obj) {
            switch (obj.event) {
                case 'addRoomDataBtn':
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
        table.on('tool(roomTable)', function (obj) {
            var RoomData = obj.data;  //获取到单击行的数据
            if (obj.event === 'delete') {
                if (typeId==1){
                    layer.msg("没有权限访问！",{
                        icon:5,
                        anim:6
                    });
                    return;
                }
                layer.confirm('确定注销【' + RoomData.roomName + '】的房间信息吗？', {icon: 3, title: '提示:'}, function () {
                    $.post("${Path}/room/deleteRoomInfo?", {id: RoomData.id}, function (returnValue) {
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
                OpenUpdateModel(RoomData);
            }
        });

        //表单验证
        form.verify({
            roomNumber: function (value) {
                var roomId = $("#roomId").val();
                var formData = new FormData();
                formData.append("roomNumber", value);
                formData.append("id", roomId);
                var message = '';
                $.ajax({
                    url: "${Path}/room/checkRoomNumber",
                    type: 'post',
                    data: formData,
                    async: false,
                    cache: false,
                    processData: false,
                    contentType: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            message = "输入的房间号已经存在，请重新输入";
                        }
                    }
                });
                if (message !== '') {
                    return message;
                }
            }
        });

        //打开添加房间信息模态框
        var maxIndex;
        var url;

        function OpenAddMenuModel() {
            maxIndex = layer.open({
                type: 1,
                title: "添加房间信息",
                content: $('#addAndEditModel'),
                area: ["700px", "410px"],
                anim: 5,
                skin: "layui-layer-molv",
                closeBtn: 2,
                success: function (index) {
                    //清空表单
                    $("#addAndEditRoomFrame")[0].reset();
                    url = "${Path}/room/addRoomInfo";
                }
            });
        }

        //打开修改房间信息模态框
        function OpenUpdateModel(data) {
            maxIndex = layer.open({
                type: 1,
                title: "修改房间信息",
                content: $('#addAndEditModel'),
                area: ["700px", "410px"],
                anim: 5,                 //动画效果
                skin: "layui-layer-molv", //皮肤
                closeBtn: 2,
                success: function (index) {
                    //给表单复制
                    form.val("addAndEditRoomFrame", data);
                    $("#roomId").val(data.id);
                    url = "${Path}/room/updateRoomInfo";
                }
            });
        }

        //保存添加或修改的房间数据
        form.on('submit(toEditAndAddSubmit)', function (data) {
            //序列化表单数据
            var params = $("#addAndEditRoomFrame").serialize();
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