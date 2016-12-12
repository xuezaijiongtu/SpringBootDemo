/*
 * <Utils> JavaScript Document 
 * Version : v_1.1
 * Require : jQuery_v2.1.0
 * Copyright © 2014 m.vip.com. All rights reserved.
 */

var VIPUtils = {

	Valid: {
		
		isMobile: function(val) {
			
			if (val.length > 11)                                                   return false;
			if (/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/.test(val))       return true;
			else                                                                   return false;
		},

		isPostCode: function(val) {
			
			if (/^[1-9][0-9]{5}$/.test(val))    return true;
			else                                return false;
		},
		
		isMail: function(val) {
			
			if (/^\w+([-+.]\w+)*@\w+([-.]\\w+)*\.\w+([-.]\w+)*$/.test(val))    return true;
			else                                                               return false;
		},

		isNumber: function(val) {
			
			if (/^\d+$/.test(val))    return true;
			else                      return false;
		},
		
		isInteger: function(val) {
			
			if (/^[-\+]?\d+$/.test(val))    return true;
			else                            return false;				
		},
		
		isDouble: function(val) {
			
			if (/^[-\+]?\d+(\.\d+)?$/.test(val))    return true;
			else                                    return false;								
		},
		
		isIDCard: function(val) {
			
			if (/^\d{15}(\d{2}[A-Za-z0-9])?$/.test(val))    return true;
			else                                            return false;								
		},
		
		isUrl: function(val) {
			
			if (/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\’:+!]*([^<>\"\"])*$/.test(val))    return true;
			else                                                                                          return false;				
		},
		
		isPassword: function(val) {
			
			if (!/^\d+$/.test(val) && !/^[A-Za-z]+$/.test(val) && /[a-zA-Z0-9]{6,16}/.test(val) && (val.length>=6 && val.length<=20 ))    return true;
			else                                                                                                                          return false;
		},
		
		isFunction: function(val) {
			
			if (typeof(val) === "function")    return true;
			else                               return false;
		},
		
		isExist: function(obj) {
			
			if (obj === undefined || obj === null || obj === "" || obj === {} || obj.length <= 0)    return false;
			else                                                                                     return true;
		}
	},

	// Cookie
	Cookie: {
		
		// Get
		get: function(name) {
			var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
			if (arr != null)  return unescape(arr[2]); return null;
		},
		
		// Set
		set: function(name, value) {
			var Days = 30;            //此 cookie 将被保存 30 天
			var exp  = new Date();    //new Date("December 31, 9998");
			exp.setTime(exp.getTime() + Days*24*60*60*1000);
			document.cookie = name + "=" + escape (value) + ";expires=" + exp.toGMTString();				
		},
		
		// Del
		del: function(name) {
			var exp = new Date();
			exp.setTime(exp.getTime() - 1);
			var cval=getCookie(name);
			if (cval!=null)  document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
		}
	},

	// Browser
	Browser: {

	   //获取垂直滚动位置
	   getScrollTop: function() {
			
			return document.documentElement.scrollTop;
	   },
	   
	   //获取水平滚动位置
	   getScrollLeft: function() {
			
			return document.documentElement.scrollLeft;
	   },
	   
		//获取视口宽度
		getClientWidth: function() {
			
			return document.documentElement.clientWidth;
		},
		
		//获取视口高度
		getClientHeight: function() {
			
			return document.documentElement.clientHeight;
		}
	},

	// Url
	Url: {
		
		/*
		 获取参数
		 @method: getParam, refresh
		 @eg: VIP.Url.getParam()["param"]
		 */
		getParam: function() {
			
			var str = window.location.href.split("?"),
				arr = new Array();  
			
			if (str.length > 1) {  
				
				var arrBuf = str[1].split("&");
				
				for (var i = 0, iLoop = arrBuf.length; i < iLoop; i++) {
					
					var aTmp = arrBuf[i].split("=");
					arr[aTmp[0]] = aTmp[1];  
				}
			}
			return arr;
		},
		
		refresh: function() {
			
			window.location.href = window.location.href;
		}
	},
	
	// Pager
	Pager: {
		
		create: function() {
			
			
		}
	},
	
	// Content Editable
	ContentEditable: function(id, ajaxUrl, callBackFn) {
		
		var target = $("#" + id);
		
		var ov = "";
		
		target.on("dblclick", '[data-role="contenteditable"]', function() {
			
			var ts = $(this);
			
			ts.attr("contenteditable", true);
			ts.addClass("editable");
			
			ov = ts.text();
		});
		
		target.on("blur", '[data-role="contenteditable"]', function() {
			
			var ts   = $(this),
			    id   = ts.data("id"),
			    name = ts.data("name"),
			    val  = ts.text();
			
			ts.attr("contenteditable", false);
			ts.removeClass("editable");
			
			if (VIPUtils.Valid.isExist(val)) {
				
				var arr = {},
				    att1 = "a",
					att2 = name;
				
				var arr    = {"id": id},
				    att    = name,
					attVal = val;
				
				arr[att] = attVal;
				
				$.post(ajaxUrl, arr, function(data) {
					
					if (data.status === 1) {
						
						if (VIPUtils.Valid.isFunction(callBackFn))    callBackFn();				
					
					} else {
						
						alert(data.msg);
					}
					
				}, "json").error(function() {
					
					ts.text(ov);
					console.log("Ajax Request Error!");
				});				
			
			} else {
				
				ts.text(ov);
				console.log("输入内容不存在");
				
				return false;
			}
		});
	}
}