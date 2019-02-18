 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    $.get('/resource/china.json').done(function (data) {
    var ajaxdata=AjaxUtil.Get("/wap/zlzx/getProvinceList");
    var datalist=[];
    var datamark={};
    if(ajaxdata.code==0){
    	$.each(ajaxdata.data,function(key, val){
    		var data={name:val.name,value:10000,code:val.code};
    		datamark[val.name]=[val.precision,val.latitude];
    		datalist.push(data);
    	});
    }
        myChart.setOption(option = {
                title : {
                    text: '',
                    subtext: '',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    show:true
                },
                dataRange: {
                    min: 0,
                    max: 2500,
                    x: 'left',
                    y: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : false,
                    show:false
                },
                toolbox: {
                    show: false,
                    orient : 'vertical',
                    x: 'right',
                    y: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: false, readOnly: false},
                        restore : {show: false},
                        saveAsImage : {show: false}
                    }
                },
                roamController: {
                    show: false,
                    x: 'right',
                    mapTypeControl: {
                        'china': false
                    }
                },
                series : [
                    {
                        type: 'map',
                        /*图片路径'， 如'image://../asset/ico/favicon.png'*/
                        mapType: 'china',
                        roam: false,
                        itemStyle: {
                            normal: {label: {show: true}},
                            emphasis: {label: {show: true}}
                        },
                        tooltip: {
                            show: false
                        },
                        markPoint : {
                            symbolSize:20,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
                            symbol:"image:///resource/pc/web/images/maptipback.png",
                            itemStyle: {
                                normal: {
                                    borderColor: '#87cefa',
                                    borderWidth: 1,            // 标注边线线宽，单位px，默认为1
                                    label: {
                                        show: true,
                                        position: 'inside',
                                        formatter: function (param) {
                                                return param.name;
                                        },
                                        margin:100,
                                        clickable:true,
                                        textStyle:{
                                            fontWeight:'bolder',
                                            fontStyle:'normal',
                                            fontSize:10,
                                            color:'#fff'
                                        }
                                    },
                                },
                                emphasis: {
                                    borderColor: '#1e90ff',
                                    borderWidth: 5,
                                    label: {
                                        show: false
                                    }
                                }
                            },
                            data : datalist
                        },
                        geoCoord:  datamark,
                        data: [
                            {name: '北京', value: Math.round(Math.random() * 1000)},
                            {name: '天津', value: Math.round(Math.random() * 1000)},
                            {name: '上海', value: Math.round(Math.random() * 1000)},
                            {name: '重庆', value: Math.round(Math.random() * 1000)},
                            {name: '河北', value: Math.round(Math.random() * 1000)},
                            {name: '河南', value: Math.round(Math.random() * 1000)},
                            {name: '云南', value: Math.round(Math.random() * 1000)},
                            {name: '辽宁', value: Math.round(Math.random() * 1000)},
                            {name: '黑龙江', value: Math.round(Math.random() * 1000)},
                            {name: '湖南', value: Math.round(Math.random() * 1000)},
                            {name: '安徽', value: Math.round(Math.random() * 1000)},
                            {name: '山东', value: Math.round(Math.random() * 1000)},
                            {name: '新疆', value: Math.round(Math.random() * 1000)},
                            {name: '江苏', value: Math.round(Math.random() * 1000)},
                            {name: '浙江', value: Math.round(Math.random() * 1000)},
                            {name: '江西', value: Math.round(Math.random() * 1000)},
                            {name: '湖北', value: Math.round(Math.random() * 1000)},
                            {name: '广西', value: Math.round(Math.random() * 1000)},
                            {name: '甘肃', value: Math.round(Math.random() * 1000)},
                            {name: '山西', value: Math.round(Math.random() * 1000)},
                            {name: '内蒙古',value: Math.round(Math.random() * 1000)},
                            {name: '陕西', value: Math.round(Math.random() * 1000)},
                            {name: '吉林', value: Math.round(Math.random() * 1000)},
                            {name: '福建', value: Math.round(Math.random() * 1000)},
                            {name: '贵州', value: Math.round(Math.random() * 1000)},
                            {name: '广东', value: Math.round(Math.random() * 1000)},
                            {name: '青海', value: Math.round(Math.random() * 1000)},
                            {name: '西藏', value: Math.round(Math.random() * 1000)},
                            {name: '四川', value: Math.round(Math.random() * 1000)},
                            {name: '宁夏', value: Math.round(Math.random() * 1000)},
                            {name: '海南', value: Math.round(Math.random() * 1000)},
                            {name: '台湾', value: Math.round(Math.random() * 1000)},
                            {name: '香港', value: Math.round(Math.random() * 1000)},
                            {name: '澳门', value: Math.round(Math.random() * 1000)}
                        ]

        }
                ]
            }
        );
    });
 myChart.on('click',  function eConsole(param) {
    if(VarUtil.isNotNull(param.data.code)){
	        // alert("测试获取iD："+param.data.dataid);
	        $("#main").hide();
	           
	        $("#allmap").show();
	       var ajaxdata=AjaxUtil.Post("/wap/zlzx/getdetailByProvince",{code:param.data.code})
	       
	        var data_info =[];
	       if(ajaxdata.code==0){
	       		$.each(ajaxdata.data,function(key,val){
	       			data_info.push([val.ex5,val.ex6,val.title,"地址："+val.ex4]);
	       		});
	       }
	        getMap(data_info,param.data.name);  
        }
    });
//-------------------------地图详情  传id获取数据------->

    // 百度地图API功能
    var map = new BMap.Map("allmap"); 
   
	// map.centerAndZoom(new BMap.Point(116.417854,39.921988), 15);
    // map.enableScrollWheelZoom();
 
    function getMap (data_info,cityname) {  
    	ismapinfo=true;    	
    	// map.centerAndZoom(new BMap.Point(log, lat), 11);  // 初始化地图,设置中心点坐标和地图级别
	     map.addControl(new BMap.MapTypeControl());   //添加地图类型控件 
	     map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放  	    
	     map.centerAndZoom(cityname,8);   	//定位城市中心
    	if(data_info&&data_info.length>0){
	     for(var i=0;i<data_info.length;i++){
	        var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
	        var content = data_info[i][3];
	        var title = data_info[i][2];
	        map.addOverlay(marker);               // 将标注添加到地图中
	        addClickHandler(content,title,marker);
	     }
	    }

    }
    
    function addClickHandler(content,title,marker){
        marker.addEventListener("click",function(e){
         var opts1 = {
	        width : 250,     // 信息窗口宽度
	        height: 80,     // 信息窗口高度
	        title : title , // 信息窗口标题
	        enableMessage:true//设置允许信息窗发送短息
	    };
            var p = e.target;
	        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	        var infoWindows = new BMap.InfoWindow(content,opts1);  // 创建信息窗口对象
	        map.openInfoWindow(infoWindows,point); //开启信息窗口
        }
        );
    }