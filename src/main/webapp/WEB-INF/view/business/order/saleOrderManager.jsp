<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>订单销售管理页面</title>
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
            <label class="layui-form-label">订单编号</label>
            <div class="layui-input-inline">
                <input type="text" name="orderId" autocomplete="off" class="layui-input" placeholder="请输入查询的订单编号">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="custPhone" lay-verify="userPhone" autocomplete="off" class="layui-input"
                       placeholder="请输入客户手机号">
            </div>
        </div>

        <div class="layui-inline">
            <div class="layui-input-label">
                <button type="button" class="layui-btn layui-icon layui-icon-search" lay-filter="doSubmit" lay-submit=""
                        id="doSearch">&nbsp;查询
                </button>
                <button type="reset" class="layui-btn layui-icon layui-btn-danger layui-icon-refresh">&nbsp;重置</button>
            </div>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="orderTable" lay-filter="orderTable"></table>

<!-- 数据表格内部操作按钮 -->
<script type="text/html" id="barDemo">
    {{#  if(d.orderState == 0){          }}
    <a class="layui-btn layui-btn-xs" lay-event="edit">查看</a>
    {{#  } else if(d.orderState == 1) {  }}
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="handle">受理</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
    {{#  } else {                        }}
    <a class="layui-btn layui-btn-xs layui-btn-normal " lay-event="finish">结算</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
    {{#  }                               }}
</script>
<!-- 数据表格结束 -->

<!-- 修改的弹出层开始 -->
<div style="display: none" id="addAndEditModel">

    <form class="layui-form layui-form-pane" action="" lay-filter="addAndEditOrderFrame" id="addAndEditOrderFrame"
          style="margin: 20px">
        <blockquote class="layui-elem-quote">
            <i class="layui-icon layui-blue">&#xe609;</i>
            订单信息管理
        </blockquote>

        <div class="layui-form-item">
            <label class="layui-form-label">订单编号</label>
            <div class="layui-input-block">
                <input type="text" name="orderId" lay-verify="required" readonly="readonly" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">足疗名称</label>
            <div class="layui-input-block">
                <input type="text" name="pedicureName" autocomplete="off" readonly="readonly" lay-verify="required"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">客户姓名</label>
            <div class="layui-input-block">
                <input type="text" name="custName" autocomplete="off" readonly="readonly" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="text" name="custPhone" autocomplete="off" readonly="readonly" lay-verify="required"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">房间号</label>
            <div class="layui-input-block">
                <input type="text" name="roomNumber" autocomplete="off" readonly="readonly" lay-verify="required" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">服务人员</label>
            <div class="layui-input-block">
                <input type="text" name="staffName" autocomplete="off" readonly="readonly" lay-verify="required"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">收费金额</label>
            <div class="layui-input-block">
                <input type="text" name="chargeAmount" autocomplete="off" readonly="readonly" lay-verify="required"
                       class="layui-input" placeholder="￥" >
            </div>
        </div>


        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注说明</label>
            <div class="layui-input-block">
                    <textarea placeholder="请输入订单修改原因" readonly="readonly" lay-verify="required" name="orderInfo"
                              class="layui-textarea"></textarea>
            </div>
        </div>

        <%--<div class="layui-form-item" align="center">
            <div class="layui-input-label">
                <button type="button" class="layui-btn layui-icon layui-icon-ok-circle  layui-btn-radius" lay-submit=""
                        lay-filter="toEditAndAddSubmit" id="toEditAndAddSubmit">&nbsp;提交
                </button>
                <button type="reset"
                        class="layui-btn layui-icon layui-btn-danger layui-icon-refresh   layui-btn-radius">&nbsp;重置
                </button>
            </div>
        </div>--%>

    </form>
</div>
<!-- 修改的弹出层结束 -->


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
            elem: '#orderTable'
            , url: '${Path}/order/loadOrderInfo'
            , height: 'full-190'
            , cellMinWidth: 100
            , page: true
            , text: {
                none: "暂无相关数据"
            }
            , title: '订单数据表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'orderId', title: '订单编号', align: "center"}
                , {field: 'pedicureName', title: '项目名称', align: "center"}
                , {field: 'custName', title: '客户姓名', align: "center"}
                , {field: 'custPhone', title: '手机号', align: "center"}
                , {field: 'roomNumber', title: '房间号', align: "center"}
                , {field: 'staffName', title: '服务人员', align: "center"}
                , {field: 'chargeAmount', title: '收费金额', width: 100, align: "center"}
                , {field: 'orderInfo', title: '备注说明', align: "center"}
                , {
                    field: 'orderState', title: '订单状态', align: "center", templet: function (data) {
                        if (data.orderState === 0) {
                            return '<font style="color: #a9a9a9;font-weight: bold" >已完成</font>';
                        }
                        if (data.orderState === 1) {
                            return '<font style="color: red;font-weight: bold" >预定中</font>';
                        }
                        if (data.orderState === 2) {
                            return '<font style="color: #1e90ff;font-weight: bold">足疗中</font>';
                        }
                    }
                }
                , {field: 'createTime', title: '创建时间', align: "center"}
                , {field: 'operName', title: '操作人', align: "center"}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 130}
            ]]
        });

        //给查询按钮添加单击事件
        $("#doSearch").click(function () {
            //获取表单提交的数据序列
            var parameter = $("#searchFrom").serialize();
            tableIns.reload({
                url: "${Path}/order/loadOrderInfo?" + parameter,
                page: {curr: 1}
            });
        });

        //表单验证
        form.verify({
            userPhone: function (value) {
                if (value !== "") {
                    var formData = new FormData();
                    formData.append("custPhone", value);
                    var message = '';
                    $.ajax({
                        url: "${Path}/order/checkCustPhone",
                        type: 'POST',
                        data: formData,
                        async: false,
                        cache: false,
                        processData: false,
                        contentType: false,
                        dataType: "json",
                        success: function (data) {
                            if (data) {
                                message = "请输入正确的客户手机号码";
                            }
                        }
                    });
                    if (message !== '') {
                        return message;
                    }
                }
            }
        });

        //监听行工具按钮事件
        table.on('tool(orderTable)', function (obj) {
            var orderData = obj.data;
            if (obj.event === 'delete') {
                deleteOrderInfo(orderData);
            } else if (obj.event === 'edit') {
                OpenUpdateModel(orderData);
            } else if (obj.event === 'handle') {
                handleOrderInfo(orderData);
            } else if (obj.event === 'finish') {
                finishOrderInfo(orderData);
            }
        });


        // 删除订单信息
        function deleteOrderInfo(orderData) {
            layer.confirm('确认终止【' + orderData.orderId + '】的订单信息吗？', {icon: 3, title: '提示:'}, function () {
                $.post("${Path}/order/deleteOrderInfo?", {orderId: orderData.orderId}, function (returnValue) {
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
        }

        //打开修改订单信息模态框
        var maxIndex;
        var url;
        function OpenUpdateModel(orderData) {
            maxIndex = layer.open({
                type: 1,
                title: "修改订单信息",
                content: $('#addAndEditModel'),
                area: ["700px", "520px"],
                anim: 5,                 //动画效果
                skin: "layui-layer-molv", //皮肤
                closeBtn: 2,
                success: function (index) {
                    //给表单赋值
                    form.val("addAndEditOrderFrame", orderData);
                    url = "${Path}/order/updateOrderInfo";
                }
            });
        }
        //保存修改的订单数据
        form.on('submit(toEditAndAddSubmit)', function (data) {
            //序列化表单数据
            var params = $("#addAndEditOrderFrame").serialize();
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

        // 处理客户的预定订单
        function handleOrderInfo(orderData) {
            layer.confirm('请确认顾客【' + orderData.custName + '】已到店内，并且开始足疗服务？', {icon: 3, title: '提示:'}, function () {
                $.post("${Path}/order/handleOrderInfo?", {orderId: orderData.orderId}, function (returnValue) {
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
        }

        // 订单结算
        function finishOrderInfo(orderData) {
            layer.confirm('顾客【' + orderData.custName + '】的足疗服务是否已结束？', {icon: 3, title: '提示:'}, function () {
                $.post("${Path}/order/finishOrderInfo?", {orderId: orderData.orderId}, function (returnValue) {
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
        }

    });


</script>

</body>
</html>