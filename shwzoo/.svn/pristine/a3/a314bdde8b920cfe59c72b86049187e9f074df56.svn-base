$(function () {
	 autoxl.bind("spuName", getSpuStartwithName,true);
    //上传图片
    $(".tjcpxx-con-form-upthis").click(function () {
    	
        $.ajaxFileUpload({
            url: "/app/api/img/upload",
            secureuri: false,
            fileElementId: 'selectimg',
            dataType: "json",
            //ftype:上传文件类型（图片文件=1，其他文件=2）
            //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
            data: { relationtype : 7 },
            type: 'POST',
            async:false,
            success: function (result) {
            	
                $("input[name='img']").val($("#imgsrc").val()+ result.data[0]);
                if (result.code == 0){
                	Dalert("上传成功");
					$("#loadimg").attr("src",
							$("#imgsrc").val() + result.data[0]);
                }
                	
				else{
					$("#loadimg").attr("src", "");
				}
                
					
                //TODO 结束正在加载中
            }
        });
    });
    //品牌列表
    getBrandlist();
    //商品分类
    Class.unit("fc");
    //专题
    getSpeicallist();
    //商品类型修改
    $("#Type").change(function () {
        var type = $(this).val();
        $("div[name='fltype']").each(function () {
            $(this).attr("style", "display:none");
        });
        switch (type) {
            case "0": {
                $("#spname").attr("style", "display:block");
                autoxl.bind("spuName", getSpuStartwithName,true);
                break;
            }
            case "1": {               
                $("#brand").attr("style", "display:block");
                break;
            }
            case "2": {
                $("#goodclass").removeAttr("style");
                break;
            }
            case "3": {
                $("#spcials").removeAttr("style");
                break;
            }
        }
    });

    var flid = GetQueryStringByName("flid");
    var id = GetQueryStringByName("id");

    //取消返回
    $("input[name=backBtn]").click(function () {
        formCancel();
    });
    
    //保存信息
    $("input[name=submit_ok]").click(function () {
    	formSubmit();
    });
    //获取信息
    if (parseInt(id) > 0) {
        GetInfo();
    } else {
        $("#spname").removeAttr("style");
        autoxl.bind("spuName", getSpuStartwithName,true);
    }
    selectChange();
});

function formCancel() {
    /*var flid = GetQueryStringByName("flid");
    var type = GetQueryStringByName("fltype");*/
    //location.href = "ProFloors?flid=" + flid + "&fltp=" + type;
	var flid=$("#flid").val();
	
    window.location.href = "/platform/floor/showProList?flid=" + flid;
	
}
function check() {
    return $("#form").validate({
        rules: {
            name: {
                required: true,
                maxlength: 200
            },
            //selectimgs: {required:true},
            spuPrice: {
                required: true
            },
            /*display: {
                required: true
            }*/
        },
        message: {
            name: { required: "商品名不可为空", maxlength: "最多输入50个汉子" },

            //selectimgs: {
            //    required: "必填"
            //},
            spuPrice: {
                required: "商品价格不可为空"
            },
           /* display: {
                digtal: "最多输入50个汉子"
            }*/
        }, errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        }
    });
}

function formSubmit() {
    //if (check().form()) {
    //var type = GetQueryStringByName("fltype");
    addInfo();
    //}
}
//品牌下拉框
function getBrandlist() {
    $.ajax({
        url: "/platform/floor/queryAllBand",
        type: "Get",
        async:false,
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                Dalert(data.desc);
            } else {
                var listdata = {
                    list: data.data
                }
                var html = template('select_brand', listdata);
                $("#brandlist").html(html);
            }
        }
    });
}
//专题下拉框
function getSpeicallist() {
    $.ajax({
        url: "/platform/floor/GetAllSpecials",
        type: "Get",
        dataType: "json",
        async:false,
        success: function (data) {
            if (data.code < 0) {
                Dalert(data.desc);
            } else {
                var listdata = {
                    list: data.data
                }
                var html = template('select_Specials', listdata);
                $("#speciallist").html(html);
            }
        }
    });
}
//商品分类调用
var Class = {
    unit: function (callback) {
        Class.getFirstClass(callback); //获取一级分类
    },
    getFirstClass: function (callback) {
        $.ajax({
            url: "/platform/floor/P_GetFirstClass",
            type: "Post",
            data: {},
            dataType: "json",
            async:false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('flist', listdata);
                    $("#defaultfc").after(html);
                    if (callback) {
                        Class.callback("fc");;
                    }
                }
            },
            error: function () {

            }
        });
    },
    firstChange: function (callback, value) {  //根据父ID获取分类
        var fid = $("#firstID").val();
        
        if (value == "fc") {
            fid = $("#firstID").val();
           
        }
        else if (value == "sc") {
            fid = $("#secondID").val();
           
        }
        $.ajax({
            url: "/platform/floor/P_GetChildrenByFatherID",
            type: "Post",
            data: { "fatherID": fid },
            dataType: "json",
            async:false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    if (value == "fc") {
                        var html = '<option value="0" >无</option>' + template('slist', listdata);
                        $("#secondID").html(html);
                        if (callback) {
                            Class.callback("sc");
                        }
                    }
                    else {
                        if (value == "sc") {
                            var html = '<option value="" >无</option>' + template('tlist', listdata);
                            $("#thirdID").html(html);
                            getthird();
                        }
                    }

                }
            },
            error: function () {

            }
        })
    },
    callback: function (value) {   //分类回调
        //var fid = $("#fid").val(), sid = $("#sid").val(); tid = $("#tid").val();
        Class.firstChange(Class.callback, value);
    }
}
//获取第三级分类
function getthird() {
    tid = $("#tid").val();
    if (tid > 0) {
        $("#thirdID option").each(function () {
            if ($(this).val() == tid) {
                $(this).attr("selected", "selected");
            } else {
                $(this).removeAttr("selected");
            }
        })
    }

}
//获取spu列表
function getSpuStartwithName(callback, event) {
    var name = $("#spname #spuName").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/floor/GetListStartwithName",
        type: "Get",
        data: { "spuName": name },
        dataType: "json",
        async:false,
        success: function (data) {

            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_spulist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.data);
            }
        }
    });
}
//保存信息
function addInfo() {
    $("input[name=submit_ok]").hide();
    var id = GetQueryStringByName("id");
    if (parseInt(id) > 0) {

    } else {
        id = 0;
    }
    var type=$("#Type").val();
    var flid =$("#flid").val();
    var img = $("#saveimg").val();
    var display = $("#display").val();
    var orderby = $("#orderby").val();
    var desc = $("#desc").val();
    var url= $("#url").val();
    var pro = 0;
    var proName = "";
    switch (type) {
        case "0": {
            pro = $("#spuName").attr("data");
            proName = $("#spuName").val();
            break;
        }
        case "1": {
            pro = $("#brandlist").val();
            proName = $("#brandlist option:selected").text();
            break;
        }
        case "2": {
            pro = $("#thirdID").val();
            proName = $("#thirdID option:selected").text();
            if (pro == 0) {
                pro = $("#secondID").val();
                proName = $("#secondID option:selected").text();
                if (pro == 0) {
                    pro = $("#firstID").val();
                    proName = $("#firstID option:selected").text();
                }
            }
            break;
        }
        case "3": {
            $("#spcials").removeAttr("style");
            pro = $("#speciallist").val();
            proName = $("#speciallist option:selected").text();
            break;
        }
    }
    $.ajax({
        url: "/platform/floor/editprofloor",
        type: "Post",
        data: { "id": id, 
        	"flID": flid, 
        	"proID": pro, 
        	"img": img, 
        	"orderby": orderby,
        	"proType":type,
        	"url":url,
        	"proName":proName,
        	"display": display, 
        	"desc": desc },
        	async:false,
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
            	var flid=$("#flid").val();
                window.location.href = "/platform/floor/showProList?flid=" + flid;
            } else {
                $("input[name=submit_ok]").show();
                Dalert(data.desc);
            }
        }
    });
}

//获取信息
function GetInfo() {
    var id = GetQueryStringByName("id");
    if (parseInt(id) > 0) {
        var pro = 0;
        $.ajax({
            url: "/platform/floor/GetProFloorByID",
            type: "Post",
            data: { "fID": id },
            dataType: "json",
            async:false,
            success: function (data) {
                if (data.code == 0) {
                    var res = data.data;
                  
                    switch (res.protype) {
                        case 0: {
                            $("#spname").attr("style", "display:block");
                            autoxl.bind("spuName", getSpuStartwithName,true);

                            //getSpuName(res.proid,setValue);
                            $("#spuName").attr("data", res.proid);
                             $("#spuName").val(res.proname);
                            break;
                        }
                        case 1: {
                            $("#brand").attr("style", "display:block");
                            $.each($('#brandlist option'),function(index,item){
                            	if(item.value==res.proid){
                            		$(this).attr("selected","selected");
                            	}
                            });
                            
                            break;
                        }
                        case 2: {
                            var thirdid=res.proid;
                            var oneId=null;
                            var twoId=null;
                            $.ajax({
                            	url:"/platform/floor/P_GetFatherByChildrenID",
                            	type: "Post",
                                data: { "id": res.proid },
                                dataType: "json",
                                cache:false,
                                async:false,
                                success: function (data){
                                	if (data.code == 0) {
                                		twoId=data.data.fatherid;
                                		
                                		 $.ajax({
                                         	url:"/platform/floor/P_GetFatherByChildrenID",
                                         	type: "Post",
                                             data: { "id": twoId },
                                             dataType: "json",
                                             cache:false,
                                             async:false,
                                             success: function (vs){
                                             	if (vs.code == 0) {
                                             		oneId=vs.data.fatherid;
                                             		
                                             	}
                                             }
                                         });
                                	}                                	
                                }
                            });
                            $("#goodclass").removeAttr("style");
                            Class.unit("fc");
                            selectSecClassId(oneId);
                            selectThrClassId(twoId);
                            
                            $.each($("#firstID option"),function(index,item){
                            	if(item.value==oneId){
                            		$(this).attr("selected","selected");
                            	}
                            });
                            $.each($("#secondID option"),function(index,item){
                            	if(item.value==twoId){
                            		$(this).attr("selected","selected");
                            	}
                            });
                            $.each($("#thirdID option"),function(index,item){
                            	if(item.value==thirdid){
                            		$(this).attr("selected","selected");
                            	}
                            });
                            
                            break;
                        }
                        case 3: {
                            $("#spcials").removeAttr("style");
                            $("#speciallist").val(res.proid);
                            break;
                        }
                    }
                    $("#Type").val(res.protype);
                    $("#loadimg").attr("src", $("#imgsrc").val()+res.img);
                    $("#saveimg").val(res.img);
                    $("#display").val(res.display);
                    $("#orderby").val(res.orderby);
                    $("#desc").val(res.description);
                    $("#url").val(res.url);
                } else {
                    Dalert(data.desc);
                }
            }
        });
    }
}

function selectSecClassId(oneId){
	 
	 $.ajax({
		 url: "/platform/floor/P_GetChildrenByFatherID",
         type: "Post",
         data: { "fatherID": oneId },
         dataType: "json",
         async:false,
	     success: function (data) {
	            if (data.code < 0) {
	                Dalert(data.desc);
	            } else {
	                var listdata = {
	                    list: data.data
	                }
	                var html = '<option value="0" >无</option>' + template('slist', listdata);
                    $("#secondID").html(html);
	            }
	        }
	    });
}

function selectThrClassId(twoId){
	 $.ajax({
		 url: "/platform/floor/P_GetChildrenByFatherID",
        type: "Post",
        data: { "fatherID": twoId },
        dataType: "json",
        async:false,
	     success: function (data) {
	            if (data.code < 0) {
	                Dalert(data.desc);
	            } else {
	                var listdata = {
	                    list: data.data
	                }
	                var html = '<option value="" >无</option>' + template('tlist', listdata);
                    $("#thirdID").html(html);
	            }
	        }
	    });
}

function selectChange() {
    if ($("#Type").val() == "0") {
        $("#url").val("/Web/Goods/pro_detail");
    } else {
        $("#url").val("");
    }
}