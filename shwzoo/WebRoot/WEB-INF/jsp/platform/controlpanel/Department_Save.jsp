<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="/resource/pc/commoncss/jQuery.Validate.css" rel="stylesheet" />
	<script type="text/javascript" src="/resource/public/platform/js/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/pc/commonjs/jquery.validate.js"></script>
	<script type="text/javascript" src="/resource/validate.js"></script>
	<script src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/departlist.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/Department.js"></script>
<link href="${pageContext.request.contextPath }/resource/public/commonjs/zTree/css/demo.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath }/resource/public/commonjs/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/commonjs/zTree/js/jquery.ztree.core-3.5.min.js"></script>


<script type="text/javascript">

    $(document).ready(function () {
        var strstatus = "";
        var status = $("#status").val();
        if (status == 0) {
            strstatus = " <input name='status' checked type='radio' value='0'>禁用 ";
            strstatus += " <span class='marrig35'></span> ";
            strstatus += " <input name='status' type='radio' value='1'>启用 ";
        }
        else {
            strstatus = " <input name='status'  type='radio' value='0'>禁用 ";
            strstatus += " <span class='marrig35'></span> ";
            strstatus += " <input name='status' checked type='radio' value='1'>启用 ";
        }
        
        $("#divStatus").html(strstatus);
        
        zTree.init();
    });

</script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">${department.tiptitle }</a><span class="sj-img"></span></li>

            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">

            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>部门编号：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="num" type="text" id="departnum" value="${department.num }">
                                <input name="Id" type="hidden" id="h_department_id" value="${department.id }" />
                                 <input type="hidden" id="status" value="${department.status}">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>部门名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="name" type="text" value="${department.name }">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->

                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>部门路径：</label></div>

                            <div class="tjcpxx-con-form1">
                                <input id="FullPath" class="tjcpxx-fprm-inp" name="FullPath" type="text" value="${department.fullpath }">
                                <input id="fid" type="hidden" name="FatherID" value="${department.fatherid }" />
                            </div>
                            <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
                                <ul id="treeDemo" class="ztree" style="margin-top:0;"></ul>
                            </div>
                        </div>
                        <!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"></div>
                            <div id="divStatus" class="tjcpxx-con-form huise">

                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="depart_action" type="hidden" value="${department.action }">
                                <span class="marrig35"></span>
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->


                    </form>
                </div>
            </div>
        </div><!--tjcpxx-con stop -->-
<script>
    function formCancel() {
        location.href = "/platform/controlpanel/department_list";
    }
</script>
