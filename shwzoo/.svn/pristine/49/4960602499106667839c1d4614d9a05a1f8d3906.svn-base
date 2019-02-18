<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link href="/resource/public/commonjs/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script type="text/javascript" src="/resource/public/platform/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/public/commonjs/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/resource/public/commonjs/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="/resource/public/seller/js/Zhgl/zTree_permission.js"></script>
</head>

<div class="clear"></div>
<div id="menuContent" class="menuContent" style="height:450px;width:490px">
    <ul id="treeDemo" class="ztree" style="height:450px;width:480px"></ul>
</div>
<div class="clear"></div>
<div style="margin-top: 35px;"></div>
<div id="the-form"style="text-align:right;">
    <input name="rpsave" type="button" value="确定" class="but-comm">
    <span style="margin-top: 15px;"></span>
    <input name="" type="button" onclick="Xclose()" value="关闭" class="but-comm">
    <input id="roleid" type="hidden" value="${roleid }" />
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var rid = ${roleid };
        var action = ${act };

        if (action == "0") {
            $("#the-form").show();
            zTree.edit(rid);
        }
        else {
            $("#the-form").hide();
            zTree.xx(rid);
        }
    })
    function Xclose(){
        parent.window.closeDialog();
    }
</script>
