<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>客户管理页面</title>
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
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="custName" autocomplete="off" class="layui-input" placeholder="请输入查询的姓名">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-inline">
                <input type="text" name="custPhone" autocomplete="off" class="layui-input" placeholder="请输入查询的号码">
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
<table class="layui-hide" id="customerTable" lay-filter="customerTable"></table>

<!-- 数据表格上方工具栏按钮 -->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-containe">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="addCustomerDataBtn">添加</button>
        <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteCustomerDataBtn">删除</button>
    </div>
</script>

<!-- 数据表格内部操作按钮 -->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>

<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none" id="addAndEditModel">

    <form class="layui-form layui-form-pane" action="" lay-filter="addAndEditCustomerFrame" id="addAndEditCustomerFrame"
          style="margin: 20px">
        <blockquote class="layui-elem-quote">
            <i class="layui-icon layui-blue">&#xe609;</i>
            客户数据管理
        </blockquote>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="custId" id="custId" style="display: none">
                <input type="text" name="custName" lay-verify="required" autocomplete="off" class="layui-input"
                       placeholder="请输入客户姓名">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">等级</label>
            <div class="layui-input-block">
                <select name="custRank">
                    <option value="">请选择客户等级</option>
                    <option value="1">黑钻会员</option>
                    <option value="2">金卡会员</option>
                    <option value="3">银卡会员</option>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <select name="custSex">
                    <option value="">请选择客户性别</option>
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">号码</label>
            <div class="layui-input-block">
                <input type="text" name="custPhone" autocomplete="off" lay-verify="required|number|phone|userPhone"
                       class="layui-input" placeholder="请输入手机号码">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="custAddress" autocomplete="off" lay-verify="required" class="layui-input"
                       placeholder="请输入地址">
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


        // 渲染表格数据
        tableIns = table.render({
            elem: '#customerTable'
            , url: '${Path}/customer/loadCustomerDataByAll'
            , toolbar: '#toolbarDemo'
            , height: 'full-190'
            , cellMinWidth: 100
            , page: true
            , text: {
                none: "暂无相关数据"
            }
            , title: '客户数据表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'custId', title: 'ID', fixed: "left", align: "center"}
                , {field: 'custName', title: '姓名', align: "center"}
                , {
                    field: 'custSex', title: '性别', align: "center", templet: function (sexData) {
                        return sexData.custSex == '1' ? '<font color="#87cefa">男</font>' : '<font color="#ffc0cb">女</font>';
                    }
                }
                , {field: 'custAddress', title: '地址', align: "center"}
                , {field: 'custPhone', title: '号码', align: "center"}
                , {
                    field: 'custRank', title: '等级', align: "center", templet: function (data) {
                        if (data.custRank === '1'){
                            return '<font style="color: black;font-weight: bold">黑钻会员</font>';
                        }
                        if (data.custRank === '2'){
                            return '<font style="color: #CD7F32;font-weight: bold">金卡会员</font>';
                        }
                        if (data.custRank === '3'){
                            return '<font style="color: #c0c0c0;font-weight: bold">银卡会员</font>';
                        }
                    }
                }
                , {field: 'createTime', title: '录入时间', align: "center"}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: "center"}
            ]]
        });

        //给查询按钮添加单击事件
        $("#doSearch").click(function () {
            //获取表单提交的数据序列
            var parameter = $("#searchFrom").serialize();
            tableIns.reload({
                url: "${Path}/customer/loadCustomerDataByAll?" + parameter,
                page: {curr: 1}
            });
        });

        //监听头工具栏添加按钮和删除按钮事件
        table.on('toolbar(customerTable)', function (obj) {
            switch (obj.event) {
                case 'addCustomerDataBtn':
                    OpenAddMenuModel();
                    break;
                case 'deleteCustomerDataBtn':
                    deleteBatchData(obj);
            }
            ;
        });

        //监听行工具删除和修改和分配按钮事件
        table.on('tool(customerTable)', function (obj) {
            var customerData = obj.data;  //获取到单击行的数据
            if (obj.event === 'delete') {
                layer.confirm('确定删除【' + customerData.custName + '】的信息吗？', {icon: 3, title: '提示:'}, function () {
                    $.post("${Path}/customer/deleteCustomerData?", {custId: customerData.custId}, function (returnValue) {
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
                OpenUpdateModel(customerData);
            }
        });

        //表单验证
        form.verify({
            userPhone: function (value) {
                var custId = $("#custId").val();
                var formData = new FormData();
                formData.append("custPhone", value);
                formData.append("custId", custId);
                var message = '';
                $.ajax({
                    url: "${Path}/customer/checkCustPhone",
                    type: 'post',
                    data: formData,
                    async: false,
                    cache: false,
                    processData: false,
                    contentType: false,
                    dataType: "json",
                    success: function (data) {
                        if (data) {
                            message = "输入的手机号已经存在，请重新输入";
                        }
                    }
                });
                if (message !== '') {
                    return message;
                }
            }
        });

        //打开添加客户信息模态框
        var maxIndex;
        var url;

        function OpenAddMenuModel() {
            maxIndex = layer.open({
                type: 1,
                title: "添加客户信息",
                content: $('#addAndEditModel'),
                area: ["700px", "470px"],
                anim: 5,
                skin: "layui-layer-molv",
                closeBtn: 2,
                success: function (index) {
                    //清空表单
                    $("#addAndEditCustomerFrame")[0].reset();
                    url = "${Path}/customer/addCustomerData";
                }
            });
        }

        //打开修改客户信息模态框
        function OpenUpdateModel(data) {
            maxIndex = layer.open({
                type: 1,
                title: "修改客户信息",
                content: $('#addAndEditModel'),
                area: ["700px", "470px"],
                anim: 5,                 //动画效果
                skin: "layui-layer-molv", //皮肤
                closeBtn: 2,
                success: function (index) {
                    //给表单复制
                    form.val("addAndEditCustomerFrame", data);
                    $("#custId").val(data.custId);
                    url = "${Path}/customer/updateCustomerData";
                }
            });
        }

        //保存添加或修改的客户数据
        form.on('submit(toEditAndAddSubmit)', function (data) {
            //序列化表单数据
            var params = $("#addAndEditCustomerFrame").serialize();
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

        //批量删除客户数据
        function deleteBatchData(obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            var data = checkStatus.data;
            var params = "";
            var customerName = "";

            if (data.length != 0) {
                $.each(data, function (i, item) {
                    if (i == 0) {
                        params += "ids=" + item.custId;
                        customerName += item.custName;
                    } else {
                        params += "&ids=" + item.custId;
                        customerName += "，" + item.custName;
                    }
                });
            }
            //判断在单击批量删除按钮之前是否有数据被选中
            if (customerName != "") {
                layer.confirm("确定删除【" + customerName + "】这些客户信息吗?", {icon: 3, title: '提示：'}, function (index) {
                    $.post("${Path}/customer/deleteBatchCustomerData", params, function (returnValue) {
                        if (returnValue.code == 200) {
                            layer.msg(returnValue.msg, {
                                icon: 6
                            });
                            //刷新数据表格
                            tableIns.reload();
                        } else {
                            layer.msg(returnValue.msg, {
                                icon: 5
                            });
                        }
                    });
                });
            } else {
                layer.msg("请先选中需要删除的客户！", {
                    icon: 5,
                    anim: 6
                });
            }

        }


    });


</script>

</body>
</html>