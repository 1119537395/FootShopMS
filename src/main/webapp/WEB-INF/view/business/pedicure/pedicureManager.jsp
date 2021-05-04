<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>足疗项目信息管理页面</title>
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
            <label class="layui-form-label">名称</label>
            <div class="layui-input-inline">
                <input type="text" name="pedicureName" autocomplete="off" class="layui-input" placeholder="请输入查询的足疗名称">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">内容描述</label>
            <div class="layui-input-inline">
                <input type="text" name="pedicureContent" autocomplete="off" class="layui-input" placeholder="请输入查询的内容描述">
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
<script type="text/html" id="pedicureState">
    <input type="checkbox" name="Unfold1" value="{{d.pedicureState}}" lay-skin="switch" lay-text="上架|下架" {{
           d.pedicureState==0?'checked':''}}>
</script>
<!-- 表格内嵌效果结束 -->

<table class="layui-hide" id="pedicureTable" lay-filter="pedicureTable"></table>

<!-- 数据表格上方工具栏按钮 -->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-containe">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="addPedicureDataBtn">添加</button>
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
    <form class="layui-form layui-form-pane" action="" lay-filter="addAndEditPedicureFrame" id="addAndEditPedicureFrame"
          style="margin: 10px">

        <div class="layui-form-item">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-block">
                <input type="text" name="pedicureId" id="pedicureId" style="display: none">
                <input type="text" name="pedicureName" lay-verify="required" autocomplete="off" class="layui-input"
                       placeholder="请输入项目名称">
            </div>
        </div>

        <div class="layui-form-item" pane="">
            <label class="layui-form-label">收费类型</label>
            <div class="layui-input-block">
                <input type="radio" name="chargeType" value="次" title="次">
                <input type="radio" name="chargeType" value="分钟" title="分钟">
                <input type="radio" name="chargeType" value="小时" title="小时">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">收费金额</label>
            <div class="layui-input-block">
                <input type="text" id="jine" name="pedicureChargeAmount" autocomplete="off" class="layui-input"
                       placeholder="￥">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">服务时间</label>
            <div class="layui-input-block">
                <input type="text" name="serviceTime" autocomplete="off" class="layui-input"
                       placeholder="请输入服务时间">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">项目状态</label>
            <div class="layui-input-block">
                <select name="pedicureState">
                    <option value="">请选择项目状态</option>
                    <option value="0">上架</option>
                    <option value="1">下架</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">内容描述</label>
            <div class="layui-input-block">
                <input name="pedicureContent" placeholder="请输入项目的内容描述" autocomplete="off" class="layui-input"
                       lay-verify="required">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注信息</label>
            <div class="layui-input-block">
                    <textarea name="pedicureInfo" placeholder="请输入备注信息" lay-verify="required"
                              class="layui-textarea"></textarea>
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
            elem: '#pedicureTable'
            , url: '${Path}/pedicure/loadPedicureInfo'
            , toolbar: '#toolbarDemo'
            , height: 'full-190'
            , cellMinWidth: 100
            , page: true
            , text: {
                none: "暂无相关数据"
            }
            , title: '足疗项目信息表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'pedicureId', title: 'ID', fixed: "left", align: "center"}
                , {field: 'pedicureName', title: '项目名称', align: "center"}
                , {field: 'pedicureContent', title: '内容描述', align: "center"}
                , {field: 'pedicureChargeType', title: '收费标准', align: "center"}
                , {field: 'pedicureChargeAmount', title: '收费金额', align: "center"}
                , {field: 'pedicureState', title: '状态', align: "center", templet: '#pedicureState'}
                , {field: 'pedicureInfo', title: '备注信息', align: "center"}
                , {field: 'createName', title: '创建人', align: "center"}
                , {field: 'pedicureCreateTime', title: '创建时间', align: "center"}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: "center"}
            ]]
        });

        //给查询按钮添加单击事件
        $("#doSearch").click(function () {
            //获取表单提交的数据序列
            var parameter = $("#searchFrom").serialize();
            tableIns.reload({
                url: "${Path}/pedicure/loadPedicureInfo?" + parameter,
                page: {curr: 1}
            });
        });


        //监听头工具栏添加按钮和删除按钮事件
        table.on('toolbar(pedicureTable)', function (obj) {
            switch (obj.event) {
                case 'addPedicureDataBtn':
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
        table.on('tool(pedicureTable)', function (obj) {
            var PedicureData = obj.data;  //获取到单击行的数据
            if (obj.event === 'delete') {
                if (typeId==1){
                    layer.msg("没有权限访问！",{
                        icon:5,
                        anim:6
                    });
                    return;
                }
                layer.confirm('确定注销【' + PedicureData.pedicureName + '】的项目信息吗？', {icon: 3, title: '提示:'}, function () {
                    $.post("${Path}/pedicure/deletePedicureInfo?", {pedicureId: PedicureData.pedicureId}, function (returnValue) {
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
                OpenUpdateModel(PedicureData);
            }
        });


        //打开添加足疗项目信息模态框
        var maxIndex;
        var url;

        function OpenAddMenuModel() {
            maxIndex = layer.open({
                type: 1,
                title: "添加足疗项目信息",
                content: $('#addAndEditModel'),
                area: ["740px", "566px"],
                anim: 5,
                skin: "layui-layer-molv",
                closeBtn: 2,
                success: function (index) {
                    //清空表单
                    $("#addAndEditPedicureFrame")[0].reset();
                    url = "${Path}/pedicure/addPedicureInfo";
                }
            });
        }


        //打开修改足疗项目信息模态框
        function OpenUpdateModel(data) {
            maxIndex = layer.open({
                type: 1,
                title: "修改足疗项目信息",
                content: $('#addAndEditModel'),
                area: ["740px", "566px"],
                anim: 5,                 //动画效果
                skin: "layui-layer-molv", //皮肤
                closeBtn: 2,
                success: function (index) {
                    //给表单复制
                    form.val("addAndEditPedicureFrame", data);
                    $("#pedicureId").val(data.pedicureId);
                    url = "${Path}/pedicure/updatePedicureInfo";
                }
            });
        }

        //保存添加或修改的足疗项目数据
        form.on('submit(toEditAndAddSubmit)', function (data) {
            //序列化表单数据
            var params = $("#addAndEditPedicureFrame").serialize();
            $.post(url, params, function (obj) {
                //弹出成功或失败的提示信息
                layer.msg(obj.msg, {
                    icon: 6,
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