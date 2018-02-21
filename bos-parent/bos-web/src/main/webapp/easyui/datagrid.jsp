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
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all.min-3.5.33.js"></script>
</head>
<script>
    $(function () {
        $("#mytable").datagrid({
            columns: [[
                {title: "编号", field: "id", checkbox: true},
                {title: "姓名", field: "name"},
                {title: "年龄", field: "age"},
                {title: "地址", field: "address"}
            ]],
            url: '${pageContext.request.contextPath}/json/datagrid_data.json',
            rownumber: true,
            toolbar: [
                {
                    text: '添加', iconCls: 'icon-add', handler: function () {
                    alert();
                }
                },
                {text: '删除', iconCls: 'icon-remove'},
                {text: '修改', iconCls: 'icon-edit'},
                {text: '查询', iconCls: 'icon-search'}
            ]
        })
    });
</script>
<body>
<table id="mytable" class="easyui-datagrid"></table>


</body>
</html>