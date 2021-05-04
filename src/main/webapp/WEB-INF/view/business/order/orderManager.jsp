<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>客户预定足疗服务页面</title>
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

<!-- 表单开始 -->
<div id="checkFormHide">
    <blockquote class="layui-elem-quote" style="border-left:0px;">
        <h2>足疗服务预定</h2>
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
                        <select name="pedicureId" lay-filter="pedicureName" id="pedicureName" lay-verify="required">
                            <option value="">请选择足疗项目</option>
                        </select>
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
                        <input type="text" name="pedicureChargeType" id="pedicureChargeType" autocomplete="off" readonly="readonly"
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
            </div>
        </form>
    </blockquote>
</div>
<!-- 表单结束 -->


<script src="${Path}/static/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['table', 'layer', 'form', 'jquery', 'upload'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var upload = layui.upload;

        // 初始化方法
        initOrderNumberFun();
        initPedicureFun();
        initRoomNumberFun();
        initStaffFun();

        // 保存订单数据
        form.on("submit(doSubmit)", function () {
            // 序列化表单数据
            var params = $("#checkFrom").serialize();
            // 发送请求保存订单数据
            $.post("${Path}/order/addOrderInfo", params, function (value) {
                if (value.code === 200) {
                    layer.msg(value.msg,{
                        icon:6
                    });
                    // 延迟刷新
                    setTimeout(function(){
                        location.reload(true);
                    },2000);
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

        // 足疗项目下拉框的监听事件
        form.on('select(pedicureName)', function (data) {
            if (data.value !== "") {
                $.post("${Path}/order/getPedicureByName",{pedicureId:data.value},function (result) {
                    $("#chargeAmount").val(result.pedicureChargeAmount);
                    $("#pedicureChargeType").val(result.pedicureChargeType);
                });
                form.render('select');
            }else {
                $("#chargeAmount").val("");
                $("#pedicureChargeType").val("");
            }
        });

        // 初始化订单编号和操作人员数据
        function initOrderNumberFun() {
            $.post("${Path}/order/initOrderNumberInfo", function (map) {
                $("#orderId").val(map.orderNumber);
                $("#operName").val(map.userName);
            });
        }
        // 初始化足疗项目下拉框
        function initPedicureFun() {
            $.post("${Path}/order/initPedicureInfo", function (result) {
                // 动态加载足疗项目下拉框
                $.each(result, function (index, item) {
                    $("#pedicureName").append("<option value='" + item.pedicureId + "'>" + item.pedicureName + "</option>");
                });
                layui.form.render("select");
            });
        }
        // 初始化房间编号下拉框
        function initRoomNumberFun() {
           $.post("${Path}/order/initRoomNumberInfo",function (result) {
               console.log(result);
                $.each(result,function (index, item) {
                    $("#roomNumber").append("<option value='" + item.id + "'>" + item.roomNumber + "</option>");
                });
               layui.form.render("select");
           });
        }
        // 初始化服务人员下拉框
        function initStaffFun() {
            $.post("${Path}/order/initStaffNameInfo",function (result) {
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