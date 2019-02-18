$(function () {
    $(".the-form-mk").each(function () {
        var url = "area.json";
        var areaJson;
        var temp_html;
        var oProvince = $(this).find("#classyj");
        var oCity = $(this).find("#classej");
        var oDistrict = $(this).find("#classsj");
        //初始化省
        var province = function () {
            $.each(areaJson, function (i, province) {
                temp_html += "<option value='" + province.ID + "'>" + province.Name + "</option>";
            });
            oProvince.html(temp_html);
            city();
        };
        //赋值市
        var city = function () {
            temp_html = "";
            var n = oProvince.get(0).selectedIndex;
            $.each(areaJson[n].Children, function (i, city) {
                temp_html += "<option value='" + city.ID + "'>" + city.Name + "</option>";
            });
            oCity.html(temp_html);
            district();
        };
        //赋值县
        var district = function () {
            temp_html = "";
            var m = oProvince.get(0).selectedIndex;
            var n = oCity.get(0).selectedIndex;
            if (typeof (areaJson[m].Children[n].Children) == "undefined") {
                oDistrict.css("display", "none");
            } else {
                oDistrict.css("display", "inline");
                $.each(areaJson[m].Children[n].Children, function (i, district) {
                    temp_html += "<option value='" + district.ID + "'>" + district.Name + "</option>";
                });
                oDistrict.html(temp_html);
            };
        };
        //选择省改变市
        oProvince.change(function () {
            city();
        });
        //选择市改变县
        oCity.change(function () {
            district();
        });
        //获取json数据
            $.ajax({
                url: "/Class/P_GetFirstClass",
                type: "Post",
                dataType: "json",
                data: {},
                success: function (data) {
                    if (data.Code < 0) {
                        alert(data.Desc);
                    } else {
                        //var listdata = {

                        //    list: data.Data
                        //}
                        //for (var i in listdata) {
                        //    if (strselect.length == 0) {
                        //        strselect += "<option value='" + i.ID + "' selected>" + i.Name + "</option>";
                        //    }
                        //    else {
                        //        strselect += "<option value='" + i.ID + "'>" + i.Name + "</option>";
                        //    }
                        //}
                        areaJson = data.Data;
                        province();
                        //$("#cid").html(strselect);
                    }
                },
                error: function () {

                }
            });
            //areaJson = data;
            
       
    });
});
