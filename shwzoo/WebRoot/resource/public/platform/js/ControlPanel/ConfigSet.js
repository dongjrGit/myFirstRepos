/*添加时间设置*/
$(document).ready(function () {
    var type = "0"
    //图标点击事件
    $(".l_fwpzmkleft").click(function () {
        var classNum = $(this).find("span").attr("class");
        if (classNum == "l_fwpztp01") {
            GetForm("0", "收货间隔时间配置"); type = "0";//配置类型
        }
        if (classNum == "l_fwpztp02") {
            GetForm("1", "收货执行时间配置"); type = "1";
        }
        if (classNum == "l_fwpztp04") {
            GetForm("2", "经营范围配置"); type = "2";
        }
        if (classNum == "l_fwpztp05") {
            GetForm("3", "员工个数配置"); type = "3";
        }
        if (classNum == "l_fwpztp06") {
            GetForm("4", "角色个数配置"); type = "4";
        }
        if (classNum == "l_fwpztp07") {
            GetForm("5", "保证金配置"); type = "5";
        }
        if (classNum == "l_fwpztp08") {//积分设置
            GetLevel();
        }
        if (classNum == "l_fwpztp09") {
            GetForm("6", "佣金返利配置"); type = "6";
        }
        if (classNum == "l_fwpztp10") {
        	GetForm("8", "抽奖次数配置"); type = "8";
        }
        if (classNum == "l_fwpztp11") {
        	GetForm("9", "中奖次数配置"); type = "9";
        }
        if (classNum == "l_fwpztp12") {
        	GetForm("7", "积分兑换人民币配置"); type = "7";
        }

    });
    //积分配置下拉框change事件
    $("#level").on("change",null, function () {
        var levelID = $("#level option:selected").val();
        GetRuleFrom(levelID, "");
    })
});
//表单验证
function check() {
    return $("#SetForm").validate({
        rules: {
            SetName: {
                maxlength: 10,
                required: true
            },
            //SetType: {
            //    required: true,
            //    digits:true
            //},
            SetValue: {
                required: true
                //digits: true
            }
        },
        //设置提示信息
        messages: {
            SetName: {
                maxlength: "长度限制在10个字符以内",
                required: "请输入配置名称"
            },
            //SetType: {
            //    required: "请输入配置类型",
            //    digits: "请输入整数"
            //},
            SetValue: {
                required: "请输入配置值"
                //digits: "请输入整数"
            }
        },
        //设置错误信息存放标签
        errorElement: "label",
        //设置验证触发事件
        focusInvalid: true

    })
}
//积分表单验证
function CheckRule() {
    return $("#pointsForm").validate({
        rules: {
            qd: {
                required: true,
                digits: true
            },
            xf: {
                required: true,
                digits: true
            },
            jf: {
                required: true,
                digits: true
            },
            hp: {
                required: true,
                digits: true
            },
            zp: {
                required: true,
                digits: true
            },
            cp: {
                required: true,
                digits: true
            }
        },
        //设置提示信息
        messages: {
            qd: {
                required: "请输入积分值",
                digits: "请输入整数"
            },
            xf: {
                required: "请输入金额值",
                digits: "请输入整数"
            },
            jf: {
                required: "请输入积分值",
                digits: "请输入整数"
            },
            hp: {
                required: "请输入积分值",
                digits: "请输入整数"
            },
            zp: {
                required: "请输入积分值",
                digits: "请输入整数"
            },
            cp: {
                required: "请输入积分值",
                digits: "请输入整数"
            }
        },
        //设置错误信息存放标签
        errorElement: "label",
        //设置验证触发事件
        focusInvalid: true

    })
}
//获取表单 st：配置type 
function GetForm(st, title) {
    if (st != "") {
        $.ajax({
            type: "post",
            url: "/platform/configset/showConfigSetByType",
            dataType: "json",
            data: { stype: st },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var p = rsl.data;
                    ConfigShow(st, title, p.keyword, p.value, p.description);
                }
                else {//当数据为空时添加默认值
                    DefSetAdd(st);
                }
            }

        });
    }
}
//添加 setType：配置类型，setname：配置名称，setValue：配置值， setDesc：配置描述
function add(setType, setname, setValue, setDesc) {
    $.ajax({
        type: "post",
        url: "/platform/configset/addConfigSet",
        dataType: "json",
        data: {
            setKey: setname, setValue: setValue, setType: setType, setDesc: setDesc, setStatus: 0
        },
        success: function (rsl) {
            if (rsl.code < 0) {
                //Dalert(rsl.Desc);
            }
        }
    });
}
//编辑setType：配置类型，setname：配置名称，setValue：配置值， setDesc：配置描述
function edit(setType, setname, setValue, setDesc) {
    //var setame = $("#SetName").val();
    //var setValue = $("#SetValue").val();
    //var setDesc = $("#SetDesc").val();
    $.ajax({
        type: "post",
        url: "/platform/configset/updateConfigSetByType",
        dataType: "json",
        data: {
            setType: setType, setKey: setname, setValue: setValue, setDesc: setDesc, setStatus: 0
        },
        success: function (rsl) {
            Dalert(rsl.desc);
        }
    });
}
//默认添加 id：配置类型
function DefSetAdd(id) {
    if (id == "0") {
        $("#SetName").attr("value", "天");
        $("#SetValue").attr("value", "5");
        $("#SetDesc").attr("value", "收货间隔5 天");
        add(id, "天", "5", "收货间隔5 天");
    }
    if (id == "1") {
        $("#SetName").attr("value", "时");
        $("#SetValue").attr("value", "5");
        $("#SetDesc").attr("value", "收货执行时间5 时");
        add(id, "时", "5", "收货执行时间5 时");
    }
    if (id == "2") {
        $("#SetName").attr("value", "经营范围");
        $("#SetValue").attr("value", "10");
        $("#SetDesc").attr("value", "经营范围 10种");
        add(id, "经营范围", "10", "经营范围 10种");
    }
    if (id == "3") {
        $("#SetName").attr("value", "员工个数");
        $("#SetValue").attr("value", "5");
        $("#SetDesc").attr("value", "最多员工个数 5人");
        add(id, "员工个数", "5", "最多员工个数 5人");
    }
    if (id == "4") {
        $("#SetName").attr("value", "角色个数");
        $("#SetValue").attr("value", "5");
        $("#SetDesc").attr("value", "最多角色个数 5个");
        add(id, "角色个数", "5", "最多角色个数 5个");
    }
    if (id == "5") {
        $("#SetName").attr("value", "店铺保证金");
        $("#SetValue").attr("value", "1000");
        $("#SetDesc").attr("value", "店铺保证金1000元");
        add(id, "店铺保证金", "1000", "店铺保证金1000元");
    }
    if (id == "6") {
        $("#SetValue").attr("value", "10");
        $("#SetDesc").attr("value", "佣金返利比率");
        add(id, "佣金返利", "10", "佣金返利比率");
    }
    if (id == "8") {
    	$("#SetName").attr("value", "次数");
        $("#SetValue").attr("value", "3");
        $("#SetDesc").attr("value", "每人每天抽奖次数");
    	add(id, "次数", "3", "每人每天抽奖次数");
    }
    if (id == "9") {
    	$("#SetName").attr("value", "次数");
        $("#SetValue").attr("value", "1");
        $("#SetDesc").attr("value", "每人每天中奖次数");
    	add(id, "次数", "1", "每人每天中奖次数");
    }
    if (id == "7") {
    	$("#SetName").attr("value", "积分兑换人民币");
        $("#SetValue").attr("value", "0.001");
        $("#SetDesc").attr("value", "积分兑换人民币比率");
    	add(id, "积分兑换人民币", "0.001", "积分兑换人民币比率1:100");
    }
}
//弹出框setType：配置类型，title：弹出框标题，setname：配置名称，setValue：配置值， setDesc：配置描述
function ConfigShow(setType, title, setame, setValue, setDesc) {
	
    if (title == "undefined" || title == "" || title == null) { title = "配置设置"; }
    var contentHTML = "";
    contentHTML += "<form id='SetForm'><div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetName'>配置名称：</label></div>";
    contentHTML += "<div class='tjcpxx-con-form1'><input class='tjcpxx-fprm-inp' id='SetName' name='SetName' type='text' value=" + setame + "></div></div>";
    if(setType=="7"){
    	contentHTML += "<div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetValue'>兑换比例：</label></div>";
    }else{
    	contentHTML += "<div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetValue'>配置值：</label></div>";
    }
    //contentHTML += "<div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetValue'>配置值：</label></div>";
    if (setType == "1") {//执行时间配置时为时间控件
        contentHTML += "<div class='tjcpxx-con-form1'><input type='text' id='SetValue' name='SetValue' onclick=\"WdatePicker({dateFmt:'HH:mm'})\" style='margin-left: 0px;' class='Wdate' value='" + setValue + "'/></div></div>";
    } else {
        contentHTML += "<div class='tjcpxx-con-form1'><input class='tjcpxx-fprm-inp' id='SetValue' name='SetValue' type='text' value=" + setValue + "></div></div>";
    }
    contentHTML += "<div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label>配置描述：</label></div>";
    contentHTML += "<div class='tjcpxx-con-form1'><textarea class='tjcpxx-con-form1-text' id='SetDesc' style='width:380px' name='SetDesc'>" + setDesc + "</textarea></div></div></form>";

    d = dialog({
        title: title,
        id: "showid",//防止重复弹出
        content: contentHTML,
        width: 550,
        height: 300,
        button: [{
            value: '保存',
            callback: function () {
                var sname = $("#SetName").val().trim();
                var sValue = $("#SetValue").val().trim();
                //if (setType == "6") {//积分设置时获取下拉框值
                //    setType = $("#SetName option:selected").val();
                //    sname = $("#SetName option:selected").text();
                //}
                if (setType == "1") {
                    sValue = sValue.toString("HH:mm");
                }
                var sDesc = $("#SetDesc").val();
                 //积分兑换人民币
                if(setType=="7"){
                	if(check1().form()){
                		edit(setType, sname, sValue, sDesc);
                	}else{
                		return false;
                	}
                }else{
                	if(check().form()){
                		edit(setType, sname, sValue, sDesc);
                	}else{
                		return false;
                	}
                }
                // if (check().form()) {
                    // edit(setType, sname, sValue, sDesc);
                // }
                // else {
                    // return false;
                // };
            },
            autofocus: true
        },
               {
                   value: '取消',
                   callback: function () {

                   }
               }
        ]
        //,modal: true //蒙层
    });
    d.show();
}
function check1() {
    return $("#SetForm").validate({
        rules: {
        	SetValue: {
				required:true,
        		number:true,
				range:[0,1]
            }
        },
        //设置提示信息
        messages: {
        	SetValue: {
				required:"请输入兑换比例",
        		number:"请输入小数",
				range:"请输入0-1之间的数值"
            }
        }
    })
}
//获取用户等级
function GetLevel() {
    $.ajax({
        type: "post",
        url: "/platform/configset/queryUsersLevel",
        dataType: "json",
        data: {
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                var option = "";
                for (var i in rsl.data) {
                    var jsonObj = rsl.data[i]
                    option += "<option value='" + jsonObj.id + "'>" + jsonObj.name + "</option>";
                }
                GetRuleFrom(rsl.data[0].id, option);

            } else {
                Dalert("未获取用户等级数据，请添加用户等级");
            }
        }

    });

}
//获取积分设置表单 levelID：用户等级id，option：等级下拉框
function GetRuleFrom(levelID, option) {
    $.ajax({
        type: "get",
        url: "/platform/configset/querPointsruleByUserLevel",
        dataType: "json",
        data: {
            levelid: levelID
        },
        success: function (res) {
            if (res.code == 0) {
                var qd = "", xf = "", jf = "", hp = "", zp = "", cp = "";
                for (var i in res.data) {
                    var jsonObj = res.data[i]
                    if (jsonObj.name == "签到") {
                        qd = jsonObj.points;
                    }
                    if (jsonObj.name == "消费") {
                        xf = jsonObj.value; jf = jsonObj.points;
                    }
                    if (jsonObj.name == "好评") {
                        hp = jsonObj.points;
                    }
                    if (jsonObj.name == "中评") {
                        zp = jsonObj.points;
                    }
                    if (jsonObj.name == "差评") {
                        cp = jsonObj.points;
                    }
                }
                if (option != "") {
                    ConfigPointsRule(option, qd, xf, jf, hp, zp, cp);
                } else {
                    $("#qd").attr("value", qd);
                    $("#xf").attr("value", xf);
                    $("#jf").attr("value", jf);
                    $("#hp").attr("value", hp);
                    $("#zp").attr("value", zp);
                    $("#cp").attr("value", cp);
                }
            }
            else {
                DefaultAdd(levelID, 5, 10, 1, 10, 5, 0);
                ConfigPointsRule(option, 5, 10, 1, 10, 5, 0);
            }
        }
    });
}
function userlevelChange() {
	var level = $("#level").val();
	$.ajax({
        type: "get",
        url: "/platform/configset/querPointsruleByUserLevel",
        dataType: "json",
        data: {
            levelid: level
        },
        success: function (res) {
            if (res.code == 0) {
                var qd = "", xf = "", jf = "", hp = "", zp = "", cp = "";
                for (var i in res.data) {
                    var jsonObj = res.data[i]
                    if (jsonObj.name == "签到") {
                        qd = jsonObj.points;
                    }
                    if (jsonObj.name == "消费") {
                        xf = jsonObj.value; jf = jsonObj.points;
                    }
                    if (jsonObj.name == "好评") {
                        hp = jsonObj.points;
                    }
                    if (jsonObj.name == "中评") {
                        zp = jsonObj.points;
                    }
                    if (jsonObj.name == "差评") {
                        cp = jsonObj.points;
                    }
                }
                $("#qd").attr("value", qd);
                $("#xf").attr("value", xf);
                $("#jf").attr("value", jf);
                $("#hp").attr("value", hp);
                $("#zp").attr("value", zp);
                $("#cp").attr("value", cp);
            }
            else {
            	$("#qd").attr("value", 0);
                $("#xf").attr("value", 0);
                $("#jf").attr("value", 0);
                $("#hp").attr("value", 0);
                $("#zp").attr("value", 0);
                $("#cp").attr("value", 0);
            }
        }
    });
}
//积分配置 弹出框 option：等级下拉框，qd：签到积分，xf：消费金额值，jf：消费金额积分，hp：好评积分，zp：中评积分，cp：差评积分
function ConfigPointsRule(option, qd, xf, jf, hp, zp, cp) {

    var contentHTML = "";
    contentHTML += "<div class='l_fwpzbg'> <form id='pointsForm'><table>";
    contentHTML += "<tr> <td class='text-right'>会员等级：</td>";
    contentHTML += "<td><select id='level' name='level' class='sel-form' onchange='userlevelChange()'>" + option;
    contentHTML += "'</select></td></tr>";

    contentHTML += "<tr><td class='text-right'>签到：</td>";
    contentHTML += "<td><input name='qd' id='qd' type='text' class='inp-seller' value='" + qd + "'></td></tr>";


    contentHTML += "<tr><td class='text-right'>消费：</td>";
    contentHTML += "<td><input name='xf' id='xf' type='text' class='inp-xd' value='" + xf + "'> 元，送 <input name='jf' id='jf' type='text' class='inp-xd'value='" + jf + "'> 积分 <label for='xf' class='error'></label><label for='jf' class='error'></label></td></tr>";


    contentHTML += "<tr><td class='text-right'>好评：</td>";
    contentHTML += "<td><input name='hp' id='hp' type='text' class='inp-seller' value='" + hp + "'></td></tr>";


    contentHTML += "<tr><td class='text-right'>中评：</td>";
    contentHTML += "<td><input name='zp' id='zp' type='text' class='inp-seller' value='" + zp + "'></td> </tr>";


    contentHTML += "<tr> <td class='text-right'>差评：</td>";
    contentHTML += " <td><input name='cp' id='cp' type='text' class='inp-seller' value='" + cp + "'></td></tr>";
    contentHTML += "  </table></form></div>";

    d = dialog({
        title: "积分配置设置",
        id: "showid",//防止重复弹出
        content: contentHTML,
        width: 550,
        height: 300,
        button: [{
            value: '保存',
            callback: function () {
                var qdp = $("#qd").val();
                var xfp = $("#xf").val();
                var jfp = $("#jf").val();
                var hpp = $("#hp").val();
                var zpp = $("#zp").val();
                var cpp = $("#cp").val();
                var levelID = $("#level option:selected").val();
                if (CheckRule().form()) {
                    var tag = 0;
                    if (qdp != qd) {
                        EditRule("签到", 0, qdp, levelID, tag);
                        tag++;

                    }
                    if (xfp != xf || jfp != jf) {
                        
                        EditRule("消费", xfp, jfp, levelID, tag);
                        tag++;
                    }
                    if (hpp != hp) {
                        
                        EditRule("好评", 0, hpp, levelID, tag);
                        tag++;
                    }
                    if (zpp != zp) {
                       
                        EditRule("中评", 0, zpp, levelID, tag);
                        tag++;
                    }
                    if (cpp != cp) {
                        
                        EditRule("差评", 0, cpp, levelID, tag);
                        tag++;
                    }
                }
                else {
                    return false;
                };
            },
            autofocus: true
        },
               {
                   value: '取消',
                   callback: function () {

                   }
               }
        ]
        //,modal: true //蒙层
    });
    d.show();
}

//默认添加积分配置 levelID：用户等级id，qd：签到积分，xf：消费金额值，jf：消费金额积分，hp：好评积分，zp：中评积分，cp：差评积分
function DefaultAdd(levelID, qd, xf, jf, hp, zp, cp) {
    AddRule(0, "签到", 0, qd, levelID);//添加签到积分
    AddRule(1, "消费", xf, jf, levelID);//消费满额 送积分
    AddRule(0, "好评", 0, hp, levelID);//添加好评积分
    AddRule(0, "中评", 0, zp, levelID);//添加中评积分
    AddRule(0, "差评", 0, cp, levelID);//添加差评积分
}
//添加积分配置 type：类型(0符合即送，1满额即送)，name：配置名称，value：满额值， points：积分值，leveID：等级id
function AddRule(type, name, value, points, levelID) {
    $.ajax({
        type: "post",
        url: "/platform/configset/addPointsrule",
        dataType: "json",
        data: {
            type: type, name: name, value: value, points: points, levelid: levelID
        },
        success: function (rsl) {
            if (rsl.code < 0) {
                //Dalert(rsl.Desc);
            }
        }
    });
}
//编辑积分配置 name：配置名称，value：满额值， points：积分值，leveID：等级id
function EditRule(name, value, points, levelID,tag) {
    $.ajax({
        type: "post",
        url: "/platform/configset/updatePointsruleByUserLevel",
        dataType: "json",
        data: {
            name: name, value: value, points: points, levelid: levelID
        },
        success: function (rsl) {
            if (rsl.code < 0) { Dalert(rsl.desc); }
            else {
                if (tag == 0) {
                    tag++;
                    Dalert(rsl.desc);
                   
                }
            }
        }
    });
}
