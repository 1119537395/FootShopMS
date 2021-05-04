<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>足疗服务页面</title>
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


<!-- 数据表格开始 -->
<!-- 表格内嵌效果按钮开始 -->
<script type="text/html" id="pedicureState">
    <input type="checkbox" name="Unfold1" value="{{d.pedicureState}}" lay-skin="switch" lay-text="上架|下架" {{
           d.pedicureState==0?'checked':''}}>
</script>
<!-- 表格内嵌效果按钮结束 -->

<!-- 数据表格 -->
<div style="display: block" id="tablePedicureId">
    <table class="layui-hide" id="pedicureTable" lay-filter="pedicureTable"></table>
</div>
<!-- 数据表格内部操作按钮 -->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="select">选择</a>
</script>
<!-- 数据表格结束 -->

<!-- 表单开始 -->
<div id="checkFormHide" style="display: none">
    <blockquote class="layui-elem-quote" style="border-left:0px;">
        <h2>足疗服务订单列表</h2>
        <hr style="background-color: dodgerblue">
        <form class="layui-form layui-form-pane" method="post" lay-filter="checkFrom" id="checkFrom">

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">订单编号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="orderId" id="orderId" autocomplete="off" readonly="readonly"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">足疗项目</label>
                    <div class="layui-input-inline">
                        <input type="text" name="pedicureName" lay-verify="required" id="pedicureName" autocomplete="off" readonly="readonly"
                               class="layui-input">
                        <!-- 接收项目ID -->
                        <input type="text" name="pedicureId" id="pedicureId" style="display: none" lay-verify="required">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">房间编号</label>
                    <div class="layui-input-inline">
                        <select name="roomId" lay-filter="roomNumber" id="roomNumber" lay-verify="required">
                            <option value="">请选择足疗房间</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">收费标准</label>
                    <div class="layui-input-inline">
                        <input type="text" name="pedicureChargeType" id="pedicureChargeType" autocomplete="off"
                               readonly="readonly"
                               class="layui-input">
                    </div>
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">服务人员</label>
                <div class="layui-input-block">
                    <select name="staffId" lay-filter="staffName" id="staffName" lay-verify="required">
                        <option value="">请选择服务人员</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">客户号码</label>
                <div class="layui-input-block">
                    <input type="text" name="custPhone" id="custPhone" autocomplete="off"
                           lay-verify="required|number|userPhone|phone"
                           class="layui-input"
                           placeholder="请输入客户的手机号码">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">收费金额</label>
                <div class="layui-input-block">
                    <input type="text" name="chargeAmount" id="chargeAmount" autocomplete="off"
                           readonly="readonly"
                           lay-verify="required|number"
                           class="layui-input" placeholder="请输入收费金额">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">操作人员</label>
                <div class="layui-input-block">
                    <input type="text" name="operName" id="operName" readonly="readonly" autocomplete="off"
                           lay-verify="required"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注说明</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入备注说明" lay-verify="required" name="orderInfo"
                              class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-label">
                        <button type="button" class="layui-btn layui-icon layui-icon-ok-circle layui-btn-normal"
                                lay-filter="doSubmit" lay-submit="" id="doSubmit">&nbsp;确认
                        </button>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-label">
                        <button type="button" class="layui-btn layui-icon layui-icon-close-fill layui-btn-normal"
                                id="cancel">&nbsp;取消
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </blockquote>
</div>
<!-- 表单结束 -->


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
            elem: '#pedicureTable'
            , url: '${Path}/order/loadPedicureInfoByState'
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
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: "center"}
            ]]
        });

        //监听行工具栏按钮事件
        table.on('tool(pedicureTable)', function (obj) {
            var PedicureData = obj.data;  // 获取到单击行的数据
            var layEvent = obj.event;     // 事件句柄
            if (layEvent === 'select'){
                selectPedicureFun(PedicureData);
            }
        });

        // 保存订单数据
        form.on("submit(doSubmit)", function () {
            // 序列化表单数据
            var params = $("#checkFrom").serialize();
            // 发送请求保存订单数据
            $.post("${Path}/order/addOrderInfoById", params, function (value) {
                if (value.code === 200) {
                    // 显示足疗项目的数据表格
                    $("#tablePedicureId").css("display", "block");
                    // 隐藏订单列表
                    $("#checkFormHide").css("display", "none");
                    layer.msg(value.msg,{
                        icon:6
                    });

                }
            });
        });


        //表单验证
        form.verify({
            userPhone: function (value) {
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
        });

        // 取消按钮的单击事件
        $("#cancel").on("click", function () {
            // 显示足疗项目的数据表格
            $("#tablePedicureId").css("display", "block");
            // 隐藏订单列表
            $("#checkFormHide").css("display", "none");
        });

        // 选择按钮的单击事件
        function selectPedicureFun(PedicureData) {
            // 在生成之前先清空
            $("#roomNumber").empty();
            $("#staffName").empty();
            // 清空表单的数据
            $("#checkFrom")[0].reset();
            // 隐藏足疗项目的数据表格
            $("#tablePedicureId").css("display", "none");
            // 显示订单列表
            $("#checkFormHide").css("display", "block");
            // 初始化足疗项目订单列表
            initOrderFormData(PedicureData);
        }

        // 初始化订单列表
        function initOrderFormData(PedicureData) {

            // 初始化订单编号和操作人名称
            $.post("${Path}/order/initOrderNumberInfo", function (map) {
                $("#orderId").val(map.orderNumber);
                $("#operName").val(map.userName);
            });

            // 初始化足疗项目
            $("#pedicureName").val(PedicureData.pedicureName);
            // 初始化收费金额
            $("#chargeAmount").val(PedicureData.pedicureChargeAmount);
            // 初始化项目ID
            $("#pedicureId").val(PedicureData.pedicureId);
            // 初始化收费标准
            $("#pedicureChargeType").val(PedicureData.pedicureChargeType);

            // 加载房间信息
            $.post("${Path}/order/initRoomNumberInfo",function (result) {
                $("#roomNumber").prepend("<option value=''>请选择房间号</option>")
                $.each(result,function (index, item) {
                    $("#roomNumber").append("<option value='" + item.id + "'>" + item.roomNumber + "</option>");
                });
                layui.form.render("select");
            });

            // 加载服务人员信息
            $.post("${Path}/order/initStaffNameInfo",function (result) {
                $("#staffName").prepend("<option value=''>请选择服务人员</option>")
                $.each(result,function (index, item) {
                    $("#staffName").append("<option value='" + item.staffId + "'>" + item.staffName + "</option>");
                });
                layui.form.render("select");
            });

        }


    });

</script>

</body>
</html>