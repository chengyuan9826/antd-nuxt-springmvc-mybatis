var basePath;
var G_userId;// 缓存变量，删除和查看用户的时候使用
var G_roleId;// 缓存变量，删除和查看角色的时候使用
var G_servProId;// 缓存变量，删除和查看角色的时候使用
var G_memberCardTypeId;// 缓存变量,查看卡类型的时候使用
var G_merberInfoId; // 缓存变量,查看和修改会员信息使用
var G_agengyInfoId; // 缓存变量,查看和修改,删除合作机构信息使用
var G_noticeInfoId; // 缓存变量,查看和修改,删除平台公告使用
var G_servStandardInfoId; //缓存变量,查看和修改,删除服务商统一结算标准使用
var G_order_state = 0; //缓存变量,要查询的工单状态值
var C;
var capacity = 10;
var G_delMsg = "确定删除吗？";
// 注册索引+1的helper
var handleHelper = Handlebars.registerHelper("addOne", function(index) {
	// 返回+1之后的结果
	return index + 1;
});
// 注册一个比较大小的Helper,判断v1是否大于v2
Handlebars.registerHelper("compare", function(v1, v2, options) {
	if (v1 > v2) {
		// 满足添加继续执行
		return options.fn(this);
	} else {
		// 不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});
// 注册一个是否相等的Helper
Handlebars.registerHelper("equals", function(v1, v2, options) {
	if (v1 == v2) {
		// 满足添加继续执行
		return options.fn(this);
	} else {
		// 不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

// 注册一个翻译用的Helper，0翻译成男，1翻译成女
Handlebars.registerHelper("transformat", function(value) {
	if (value == 0) {
		return "男";
	} else if (value == 1) {
		return "女";
	}
});

// 注册一个翻译用的Helper，0翻译成第一个参数，1翻译成第二个参数
Handlebars.registerHelper("translation", function(value, v1, v2, v3) {
	if (value == v1) {
		return v2;
	} else {
		return v3;
	}
});

/**
 * 常用的js校验
 */
var Validator = {
	phone : function(phoneNum) {
		var bValidate = RegExp(
				/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$/)
				.test(phoneNum);
		if (bValidate) {
			return true;
		} else
			return false;
	},
	email : function(email) {
		var bValidate = RegExp(
				/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)
				.test(email);
		if (bValidate) {
			return true;
		} else
			return false;
	}
};

/**
 * 把form中的参数转化为json的
 */
$(document).ready(function() {
	basePath = $("#basePath").val();
	carsUrl = basePath + "/dimCar/queryCarAll.do";//查询维修车型
	highwayUrl = basePath + "/dimHighway/queryHighwayAll.do";//查询高速信息
	itemUrl = basePath + "/dimServiceItem/queryServiceItemAll.do";//查询服务项目
	dimTroubleUrl = basePath+"/dimTroubleClassify/queryTroubleClassifyAll.do";//查询所有服故障类型
	queryTouristInfoUrl = basePath+"/touristInfo/getTouristInfo.do";//根据手机号查询游客信息 参数：mobile
	queryThirdCustomerInfoUrl = basePath+"/thirdCustomerInfo/getThirdCustomerInfo.do";//根据手机号查询第三方信息 参数：mobile
	queryCusInfo = basePath + "/memberInfo/getMemberInfo.do";//根据会员卡号查询会员信息 参数：cardNum
	cusSourceUrl = basePath+"/dimCustomerSource/queryCustomerSourceAll.do"; //查询所有客户来源
	queryFinanceNum = basePath+"/agencyInfo/getAgencyInfo.do";//根据ID查询机构详细信息 参数：id
	queryProviderList = basePath+"/serv/queryProviderList.do";//查询所有服务提供商列表
	queryProviderById = basePath+"/serv/queryProById.do";//查询服务商详情，参数servId
	queryNoticeList = basePath + "/noticeInfo/homeNoticeList.do";  //平台公告列表
	
	C = $("#contents_right");// 主体部分定义，以后可以直接用C.html(data)来刷新页面
	C.href = function(url, data) {
		$.post(url, data, function(d) {
			C.html(d);
		});
	};
	// 模拟点击第一个链接，填充右部分内容
	$("div.contents_left a:first").trigger("click");
	//失败工单
	$("#menu53").click(function(){
		G_order_state = 1;
	});
	$("#menu48").click(function(){
		G_order_state = 0;
	});
	//动态调用公告
	C.href(queryNoticeList);
});
/**
 * 根据级别和父ID查询地区列表
 * @param level
 * @param pId
 */
function getAreaByLevel(level, pId) {
	var url = basePath + "/dimArea/queryAreaAll.do";
	$.post(url, {
		parentId : pId
	}, function(data) {
		var html = handle(data);
		$("#level" + level + "Area").html(html);
	}, "json");
}

/**
 * 根据级别和父ID查询地区列表(主要针对接单里面调度里面的地区)
 * @param level
 * @param pId
 */
function getRepAreaByLevel(level, pId) {
	var url = basePath + "/dimArea/queryAreaAll.do";
	$.post(url, {
		parentId : pId
	}, function(data) {
		var html = handle(data);
		$("#repairAreaL" + level + "Id").html(html);
	}, "json");
}

/**
 * 显示加载中样式
 */
function showLoading() {
	$(".data-loading").show();
}
/**
 * 隐藏加载中样式
 */
function hideLoading() {
	$(".data-loading").hide();
}
// 设置全局ajax同步
var G_sync = function() {
	$.ajaxSetup({
		async : false
	});
};
// 设置全局ajax异步
var G_async = function() {
	$.ajaxSetup({
		async : true
	});
};
// 获取浏览器窗口的宽和高
function getViewport() {
	if (document.compatMode == "BackCompat") {
		return {
			width : document.body.clientWidth,
			height : document.body.clientHeight
		}
	} else {
		return {
			width : document.documentElement.clientWidth,
			height : document.documentElement.clientHeight
		}
	}
}
// 用ajax获取json的简便方法
function getJson(url, data, callback) {
	$.post(url, data, function(d) {
		callback(d)
	}, "json");
}
