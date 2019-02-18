//初始化
var Init = {
    bind: function () {
 
        //初始化图片上传控件
        InitImg();
        
     
        //获取出生日期、月收入水平数据
        GetSelectData();
        //区域数据绑定
        BindRegion();

        //买家数据绑定
        BindMemberData();
        
        
    }
}

//基本信息表单验证
var Vaildate = {
    bind: function () {
        $("#saveMemberBasicInfoForm").validate({
            rules: {
                text_nickname: {
                    required: true
                },
                text_realname: {
                    required: true
                }
            },
            messages: {
                text_nickname: {
                    required: "请输入昵称"
                },
                text_realname: {
                    required: "请输入真实姓名"
                }
            },
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            debug: true,
            submitHandler: function (form) {
                $(form).ajaxSubmit(Submit.bind());
            }
        })
    }
}
//基本信息表单提交
var Submit = {
    bind: function () {
    
        //昵称
        var nickname = $("#text_nickname").val();
        //性别
        var sex = $("input[name='radio_sex']:checked").val();
        //生日
        var BYear = $(".birth-year").val();
        var BMonth = $(".birth-month").val();
        var BDay = $(".birth-day").val();
        //兴趣爱好
        var inertests = "";
        //真实姓名
        var realname = $("#text_realname").val();
        //所在地
        var provincecode = "";
        var provincename = "";
        if ($("#select_province").val() != "-1") {
            provincecode = $("#select_province").val();
            provincename = $("#select_province  option:selected").text();
        }
        var citycode = "";
        var cityname = "";
        if ($("#select_city").val() != "-1") {
            var citycode = $("#select_city").val();
            var cityname = $("#select_city  option:selected").text();
        }
        var areacode = "";
        var areaname = "";
        if ($("#select_area").val() != "-1") {
            areacode = $("#select_area").val();
            areaname = $("#select_area  option:selected").text();
        }
        //详细地址
        var address = $("#text_address").val();

        $.ajax(({
            type: "post",
            url: "/pc/user/updBaseUserInfo",
            dataType: "json",
            data: {
                "NickName": nickname, 
                "RealName": realname,
                "Sex": sex,
                "BirthYear": BYear,
                "BirthMonth": BMonth, 
                "BirthDay": BDay,
                "ProvinceName": provincename,
                "ProvinceCode": provincecode, 
                "CityName": cityname, 
                "CityCode": citycode,
                "AreaName": areaname, 
                "AreaCode": areacode,
                "Address": address,
                "ch":1
                /*Interests: inertests*/
            },
            success: function (rsl) {
           
                if (rsl.code == 0) {
                    alert("保存成功");
                    refresh();
                    //location.reload();
                }
                else {
                    alert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}

//更多信息表单验证
var VaildateOther = {
    bind: function () {
        $("#saveMemberOtherInfoForm").validate({
            rules: {
                text_card: {
                    isIdCardNo: true
                }
            },
            messages: {
                text_card: {
                    isIdCardNo: "请输入正确格式的身份证号码"
                }
            },
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            debug: true,
            submitHandler: function (form) {
                $(form).ajaxSubmit(SubmitOther.bind());
            }
        })
    }
}
//更多信息表单提交
var SubmitOther = {
    bind: function () {
    	
        //婚姻状态
        var marital = $("input[name='radio_marital']:checked").val();
        //身份证号码
        var card = $("#text_card").val();
        //月收入
        incomemonth = $("#select_incomemonth").val();
        	
        $.ajax(({
            type: "post",
            url: "/pc/user/updMoreInfo",
            dataType: "json",
            data: {
                "MaritalStatus": marital,
                "IDCard": card,
                "IncomeMonth": incomemonth
            },
            success: function (rsl) {
            	
                if (rsl.code == 0) {
                    alert("保存成功"); refresh();
                    // location.reload();
                }
                else {
                    alert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}


function InitImg() {
    $("#img_submit").on("click", function (e) {
    	 $.ajaxFileUpload({
             url: "/app/api/img/upload",//UploadFile
             secureuri: false,
             fileElementId: 'file_userimg',
             dataType: "json",
             //ftype:上传文件类型（图片文件=1，其他文件=2）
             //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
             data: { relationtype : 5 },
             type: 'POST',
             success: function (result) {
             	
                 if (result.code == 0) {
                     var data = result.data[0];
                     if (data == "" || data == undefined) {
                         alert("头像上传失败！"); $("#img_showuserimg").attr("src", "");
                     } else {
                         $("#img_showuserimg").attr("src", data);
                         $("#img_headscr").attr("src", data);
                       
                         SubmitUserImg(data);
                      /*   
                     	$("#loadimg").attr("src",
    								$("#imgsrc").val() + data);*/
                         //保存图片路径到数据库
                       /*  SubmitUserImg(data.Url);
                         AddUpLoadRecords(data.GroupName, data.ServerName, data.LocalName, data.FileType, "0", data.CreateTime, "0", "1", data.Url);*/
                     }

                 } else {
                     alert(result.desc);
                     $("#img_showuserimg").attr("src", "");
                 }
                 //TODO 结束正在加载中
             },
             error: function (e) {
                 alert(JSON.stringify(e));
                 //TODO 结束正在加载中
             }
         });
    });
}

function SubmitUserImg(url) {
	
    $.ajax(({
        type: "post",
        url: "/pc/user/updUserImg",
        dataType: "json",
        data: {
            "ImgUrl": url
        },
        success: function (rsl) {
        	
            if (rsl.code == 0) {
            }
            else {
                alert(rsl.desc);
            }
        },
        error: function (e) {

        }
    }));
}
//添加文件上传服务器记录
/// <param name="gName">服务组名称（默认group1）</param>
/// <param name="sName">服务文件名称</param>
/// <param name="lName">本地文件名称</param>
/// <param name="fType">文件类型(jpg,bmp,gif,doc,png,xls )</param>
/// <param name="reId">关联Id(例如：关联产品，就是产品Id)主要以后图片维护会用到</param>
/// <param name="createTime">创建时间</param>
/// <param name="status">状态(0 正常 1删除)</param>
/// <param name="reType">关联类型(0 产品 1 用户头像)</param>
/// <param name="url">前台显示路径</param>
function AddUpLoadRecords(gName, sName, lName, fType, reId, cTime, status, reType, url) {
    $.ajax({
        type: "post",
        url: "/UpLoad/AddFileRecords",
        dataType: "json",
        data: {
            groupName: gName, serverName: sName, localName: lName, fileType: fType, relationId: reId,
            createTime: cTime, status: status, relationType: reType, url: url
        },
        success: function (rsl) {
            if (rsl.Code == 0) {
            }           
        },
        error: function (e) {

        }
    });
}

function GetSelectData() {
    //出生日期数据绑定
    var options = {
        "dateFormat": "bigEndian",
        "wraper": "span",
        "maxAge": "100",
    };
    $(".div_birthday").birthdaypicker(options);
    $(".birth-year").addClass("smallbj-sel");
    $(".birth-month").addClass("smallbj-sel");
    $(".birth-day").addClass("smallbj-sel");


    //月收入水平类型数据绑定
    $.ajax(({
        type: "post",
        url: "/pc/user/getIncome",
        dataType: "json",
        async: false,
        data: {},
        success: function (rsll) {
        	
        	rsll= eval('(' + rsll + ')');
            if (rsll.code == 0) {
                var listdata = {
                    list: rsll.data
                }
                var html = template('incomemonthtypeselect', listdata);
                $("#select_incomemonth").append(html);
            }else {
               // alert(rsll.desc);
            }
        },
        error: function (es) {

        }
    }));
}

function GetRegionData(rType, pCode) {
    $.ajax(({
        type: "post",
        url: "/pc/user/selectAreas",
        dataType: "json",
        async: false,
        data: { "RegionType": rType,
        	"ParentCode": pCode },
        success: function (rsl) {
            if (rsl.code == 0) {
                var listdata = { list: rsl.data };
                var html;
                switch (rType) {
                    case 0:
                        html = template('proviceselect', listdata);
                 
                        $("#select_province").append(html);
                        break;
                    case 1:
                        html = template('cityselect', listdata);
                        $("#select_city").append(html);
                        break;
                    case 2:
                        html = template('areaselect', listdata);
                        $("#select_area").append(html);
                        break;
                    default:
                        break;
                }
            }
            else {
                alert(rsl.desc);
            }
        },
        error: function (e) {

        }
    }));
}

function BindRegion() {
    //省数据绑定
    GetRegionData(0, "");

    //省改变 
    $("#select_province").change(function () {
        var p1 = $(this).children('option:selected').val();
        $.ajax(({
            type: "post",
            url: "/pc/user/selectAreas",
            dataType: "json",
            data: { "RegionType": 1,
            	"ParentCode": p1 },
            success: function (rsl) {
            
                if (rsl.code == 0) {
                    var listdata = { list: rsl.data }
                    var html = template('cityselect', listdata);
                    $("#select_city option[value!='-1']").remove();
                    $("#select_area option[value!='-1']").remove();
                    $("#select_city").append(html);
                }
                else {
                    alert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    })

    //市改变
    $("#select_city").change(function () {
        var p1 = $(this).children('option:selected').val();
        $.ajax(({
            type: "post",
            url: "/pc/user/selectAreas",
            dataType: "json",
            data: { "RegionType": 2,
            	"ParentCode": p1 ,
            	"ch":1},
            success: function (rsl) {
            	
                if (rsl.code == 0) {
                    var listdata = { list: rsl.data }
                    var html = template('areaselect', listdata);
                    $("#select_area option[value!='-1']").remove();
                    $("#select_area").append(html);
                }
                else {
                    alert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    })
}

function BindMemberData() {
	
    $.ajax(({
        type: "post",
        url: "/pc/user/selectInfo",
        dataType: "json",
        data: {},
        success: function (rsl) {
            if (rsl.code == 0) {
                var jsonObj = rsl.data;
                //用户名
                $("#p_username").html('用户名：' + jsonObj.username);
                //可用积分
                $("#p_usablepoints").html('可用积分：' + jsonObj.point);
                //用户级别
                //$("#p_userlevel").html('用户级别：'+jsonObj.);
                //昵称
                $("#text_nickname").attr("value", jsonObj.nickname);
                //性别
                if (jsonObj.sex == 1) {
                    $("#radio_man").attr("checked", true);
                }
                else if (jsonObj.sex == 2) {
                    $("#radio_woman").attr("checked", true);
                }
                else {
                    $("#radio_secret").attr("checked", true);
                }
                //生日
                var stryear = 0;
                if (jsonObj.b_year != null && jsonObj.b_year != "") {
                    stryear = jsonObj.b_year;
                }
                var strmonth = 0;
                if (jsonObj.b_month != null && jsonObj.b_month != "") {
                    strmonth = jsonObj.b_month;
                }
                var strday = 0;
                if (jsonObj.b_day != null && jsonObj.b_day != "") {
                    strday = jsonObj.b_day;
                }
               
                $(".birth-year").attr("value", stryear);
              
               // $(".birth-year").val('"'""'"');
                
                var select=document.getElementById("year");
                for(var i=0; i<select.options.length; i++){  
                    if(select.options[i].innerHTML == stryear){  
                    	select.options[i].selected = true;
                       
                        break;
                    }else{
                        	continue;
                        }
               }
                
               // $(".birth-year").find("option[text='stryear']").attr("selected",true);
                $(".birth-month").attr("value", strmonth);
                var select=document.getElementById("month");
                for(var i=0; i<select.options.length; i++){  
                    if(select.options[i].innerHTML == strmonth){  
                    	select.options[i].selected = true;
                       
                        break;
                    }else{
                        	continue;
                        }
               }
                $(".birth-day").attr("value", strday);
                var select=document.getElementById("day");
                for(var i=0; i<select.options.length; i++){  
                    if(select.options[i].innerHTML == strday){  
                    	select.options[i].selected = true;
                       
                        break;
                    }else{
                        	continue;
                        }
               }
                
                //所在地
                var strprovincecode = -1;  
                if (jsonObj.provinceCode != null && jsonObj.provinceCode != "") {
                    strprovincecode = jsonObj.provinceCode;
                }
                var strcitycode = -1;
                if (jsonObj.cityCode != null && jsonObj.cityCode != "") {
                    strcitycode = jsonObj.cityCode;
                }
                var strareacode = -1;
                if (jsonObj.areaCode != null && jsonObj.areaCode != "") {
                    strareacode = jsonObj.areaCode;
                }
                
                $("#select_province").attr("value", strprovincecode);
                var opList = document.getElementById("select_province").childNodes;
               
                for (var i = 0, len = opList.length; i < len; i++) {
                	
                    if (opList[i].value ==strprovincecode) {
                        opList[i].selected = true;
                        break;
                    }
                }
               

                GetRegionData(1, strprovincecode);
                $("#select_city").attr("value", strcitycode);
                
                var opList = document.getElementById("select_city").childNodes;
                
                for (var i = 0, len = opList.length; i < len; i++) {
                    if (opList[i].value ==strcitycode) {
                        opList[i].selected = true;
                        break;
                    }
                }
               
                
                GetRegionData(2, strcitycode);
                $("#select_area").attr("value", strareacode);
                
                var opList = document.getElementById("select_area").childNodes;
                
                for (var i = 0, len = opList.length; i < len; i++) {
                    if (opList[i].value ==strareacode) {
                        opList[i].selected = true;
                        break;
                    }
                }
               

                //真实姓名
                $("#text_realname").attr("value", jsonObj.realname);
                
                //详细地址
                $("#text_address").attr("value", jsonObj.address);
                
             
                //头像地址
                $("#img_showuserimg").attr("src", jsonObj.imgurl);
               $("#img_headscr").attr("src", jsonObj.imgurl);
                
                //婚姻状况
                if (jsonObj.maritalStatus == 1) {
                    $("#radio_no_marital").attr("checked", true);
                }
                else if (jsonObj.maritalStatus == 2) {
                    $("#radio_yes_marital").attr("checked", true);
                }
                else {
                    $("#radio_secret_marital").attr("checked", true);
                }
               
                //收入水平
                if (jsonObj.incomeMonth == null || jsonObj.incomeMonth == "") {
                    jsonObj.incomeMonth = "-1";
                }
                $("#select_incomemonth").attr("value", jsonObj.incomeMonth);
                
                var opList = document.getElementById("select_incomemonth").childNodes;
                
                for (var i = 0, len = opList.length; i < len; i++) {
                    if (opList[i].value ==jsonObj.incomeMonth) {
                        opList[i].selected = true;
                        break;
                    }
                }
                
                
                //证件类型
                if (jsonObj.IDCardType != null || jsonObj.IDCardType == "1") {
                    $("#text_card").attr("value", jsonObj.idcard);
                }
                
              
                //邮箱
                var emailhtml = "";
                if (jsonObj.email == null || jsonObj.email == undefined || jsonObj.email == "")
                    emailhtml = '<span class="huise ml5">未绑定</span><span class=" shenlan ml10"><a href="/member/userInfo/bindingEmail.html?setup=1">去设定</a></span>';
                else if (jsonObj.isEmailCheck == null || jsonObj.isEmailCheck == "" || !jsonObj.isEmailCheck)
                    emailhtml = jsonObj.email + '<span class=" shenlan ml10"><a href="/member/userInfo/changeEmail.html?setup=1">修改</a></span>';
                else
                    emailhtml = jsonObj.email + '<span class=" shenlan ml10"><a href="/member/userInfo/changeEmail.html?setup=1">修改</a></span><span class="huise">已验证</span>';
                $("#td_email").html(emailhtml);
                
            }
            else {
                alert(rsl.desc);
            }
        },
        error: function (es) {
        	
        }
    }));
}

function refresh() {
    location.reload();
}