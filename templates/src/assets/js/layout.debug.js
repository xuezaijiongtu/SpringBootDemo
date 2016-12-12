/*
 * <Layout> JavaScript Document 
 * Version : v_1.1
 * Require : jQuery_v2.1.0
 * Copyright © 2014 m.vip.com. All rights reserved.
 */
export default function(vm){
	
	var VIP = (function() {

		// DOM
		var Dom = {
			
			treeMenu   : $(".tree-menu"),
			sidebarBtn : $("#sidebar-btn")
		};

	    // Main Menu
		var MainMenu = {

			init : function() {
				
				// 初始化选中效果
				VIP.TreemMenu.trigger(vm.menu_config, "m0");
				Dom.treeMenu.find("li:first").addClass("on");
				
				// 绑定事件
				$(".main-menu > a, .main-menu .btn-group").on("click", function() {

					var ts = $(this),
					    id = ts.data('id');
					
					if (ts.hasClass("on")) {
						
						return false;
						
					} else {

						ts.addClass("on").siblings().removeClass("on");
						VIP.TreemMenu.trigger(menu_config, id);
						
						if (ts.index() === 0) {
							
							Dom.treeMenu.find("li:first").addClass("on");
						}
					}
				});
			}
		};

	    // Tree Menu
		var TreemMenu = (function() {

			// 生成菜单
			var trigger = function(data, id) {

				// Generate HTML
				var htm = "";

				if (id) {

					for (var i in data) {
						
						if (data[i].id === id)    htm += _generateTreeMenuHtml(data[i]);
					}
				
				} else {

					htm += _generateTreeMenuHtml(data[0]);
				}
				
				Dom.treeMenu.html(htm);
				Dom.treeMenu.find(".haschild").eq(0).addClass("open");  // 默认打开第一个二级菜单

				// Bind Event
				var menu     = Dom.treeMenu.find(".menu"),
				    menuItem = Dom.treeMenu.find("li");
				
				menuItem.on("click", function(ev) {
					
					var ts = $(this);
					
					if (ts.hasClass("hassubchild")) {
						
						if (ts.hasClass("open")) {
							
							//console.log("case 1");
							ts.removeClass("open");
							
						} else {
							
							//console.log("case 2");
							menu.find(".hassubchild").removeClass("open");
							ts.addClass("open");
						}
						
						ev.stopPropagation();
						
					} else if (ts.hasClass("haschild")) {
																
						if (ts.hasClass("open")) {
							
							//console.log("case 3");
							menuItem.removeClass("open");
							
						} else {
							
							//console.log("case 4");
							menuItem.removeClass("open");
							ts.addClass("open");
						}
						
						ev.stopPropagation();
						
					} else {
							
						//console.log("case 6");
						menuItem.removeClass("open, on");
						ts.addClass("on");
						
						ev.stopPropagation();
					}							
				});
			};

			// 展开菜单
			var show = function() {

				$("#sidebar").show();
				$("#main").css("left", 180);
				$("#sidebar-btn").removeClass("close");
			};

			// 收起菜单
			var hide = function() {

				$("#sidebar").hide();
				$("#main").css("left", 0);
				$("#sidebar-btn").addClass("close");
			};

			// 初始化
			var init = function() {

				Dom.sidebarBtn.on("click", function() {

					var ts = $(this);

					if (ts.hasClass("close"))    VIP.TreemMenu.show();
					else                         VIP.TreemMenu.hide();
				});
			};

			// 生成子菜单结构
			function _generateTreeMenuHtml(data) {

				var htm = "";
				var menuSize = data.menu.length;

				for (var i = 0; i < menuSize; i++) {
					
					var lv2MenuSize = data.menu[i].items.length;
					
					if (lv2MenuSize > 0) {  // 判断是否有二级菜单

					    htm += '<li class="haschild"><a href="javascript:void(0);">' + data.menu[i].text + '</a><ul class="menu">';
						
						for (var j = 0; j < lv2MenuSize; j++) {
							
							var lv3MenuSize = data.menu[i].items[j].items.length;
							
							if (lv3MenuSize > 0) {  // 判断是否有三级菜单
								
								htm += '<li class="hassubchild"><a href="javascript:void(0);">' + data.menu[i].items[j].text + '</a><ul class="menu lv-3">';
								
								for (var k = 0; k < lv3MenuSize; k++) {

									htm += '<li><a href="' + data.menu[i].items[j].items[k].href + '" target="main-frame">' + data.menu[i].items[j].items[k].text + '</a></li>';
								}
								htm += '</ul></li>';
								
							} else {
								
								htm += '<li><a href="' + data.menu[i].items[j].href + '" target="main-frame">' + data.menu[i].items[j].text + '</a></li>';
							}
						}
						htm += '</ul></li>';
					
					} else {

						htm += '<li><a href="' + data.menu[i].href + '" target="main-frame">' + data.menu[i].text + '</a></li>';						
					}
				}

				return htm;
			}

			// RETURN
			return {

				"trigger" : trigger,
				"show"    : show,
				"hide"    : hide,
				"init"    : init
			}

		})();

		// FullScreen Dialog
		var FullScreenDialog = {

			show: function(msg) {

				$("#fs-dialog").modal('show');
				$("#fs-dialog").find(".modal-body").text(msg);
			},

			hide: function() {

				$("#fs-dialog").modal('hide');
			}
		};

		// RETURN
		return {

			"MainMenu"         : MainMenu,
			"TreemMenu"        : TreemMenu,
			"FullScreenDialog" : FullScreenDialog
		}

	})();


	$(function($) {

		VIP.MainMenu.init();
		VIP.TreemMenu.init();
	});
}