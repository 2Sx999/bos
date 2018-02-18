<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>easyui测试</title>
    <!-- 导入easyui类库 -->
    <link id="easyuiTheme" rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ztree/zTreeStyle.css" type="text/css">
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all.min-3.5.33.js"></script>
</head>
<script type="text/javascript">
    var settings = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                //根据json对象中的page属性,判断是否为父节点
                if (treeNode.page !== undefined) {
                    //判断tab是否已经打开过
                    if ($("#tabs").tabs("exists", treeNode.name)) {
                        //打开过,则选中
                        $("#tabs").tabs("select", treeNode.name);
                    } else {
                        //没打开过,则添加
                        $("#tabs").tabs("add", {
                            title: treeNode.name,
                            closable: true,
                            content: '<iframe frameborder="0" height="100%" width="100%" src="${pageContext.request.contextPath}/' + treeNode.page + '"></iframe>'
                        })
                    }
                }
            }
        }
    };
    $.ajax({
        "url": "${pageContext.request.contextPath}/json/menu.json",
        "data": {},
        "type": "POST",
        "success": function (data) {
            $.fn.zTree.init($("#ztree"), settings, data);
        },
        "error": function () {
            alert("服务器繁忙，请稍后重试");
        },
        "dataType": "json"
    });

</script>
<body class="easyui-layout">

<div data-options="region:'north'" style="height: 50px">a</div>
<div data-options="region:'south'" style="height: 40px;">a</div>
<div data-options="region:'east'" title="East" style="width: 100px;">a</div>
<div data-options="region:'west'" title="West" style="width: 200px;">
    <!-- 制作accordion折叠面板
    fit:true----自适应(填充父容器)
    -->
    <div class="easyui-accordion" data-options="fit:true">
        <!-- 使用子div表示每个面板 -->
        <div data-options="iconCls:'icon-cut'" title="面板一">
            <ul id="ztree" class="ztree"></ul>
        </div>
        <div title="面板二"><a href="page_base_staff.action">asdfafds</a></div>
        <div title="面板三">3333</div>
    </div>

</div>
<div data-options="region:'center'">
    <div id="tabs" class="easyui-tabs" data-options="fit:'true'"></div>
</div>

</body>
</html>