<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="/resource/public/commonjs/zTree/css/demo.css" rel="stylesheet" />
<link href="/resource/public/commonjs/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script type="text/javascript" src="/resource/public/platform/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/public/commonjs/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/resource/public/commonjs/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="/resource/public/platform/js/ControlPanel/zTree_permission.js"></script>

<div id="menuContent" class="menuContent" style="height:450px;width:500px">
    <ul id="treeDemo" class="ztree" style="margin-top:0;height:450px;width:490px"></ul>
</div>
<div class="clear"></div>
<div style="margin-top:20px;"></div>
<div id="the-form" style="text-align:right;">
    <input class="inquire" name="rpsave" type="button" value="确定">
    <span class="marrig10"></span>
    <input class="inquire" name="" type="button" onclick="Xclose()" value="关闭">
    <input id="roleid" type="hidden" value="${roleid }" />
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var rid = ${roleid };
        var roleaction =${act };
        var roletype = ${roletype };
       
        if (roleaction == "0") {
            $("#the-form").show();
            zTree.edit(rid,roletype);
        }
        else {
            $("#the-form").hide();
            zTree.xx(rid,roletype);
        }
    })
    function Xclose(){
        parent.window.closeDialog();
    }
</script>