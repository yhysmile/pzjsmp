if(typeof window.mf == "undefined"){
	window.mf = {};
}
if(typeof window.mf.msg == "undefined"){
	window.mf.msg = {};
}
(
 	function (package) {
		/**
		 *	模拟浏览器alert弹出消息框
		 *  参数：msg 消息内容 width:消息框宽度，可不填
		 */
		package.alert = function(msg,width){
			var divid = "dialog" + parseInt(10000000*Math.random());
			if(typeof msg == "undefined"){
				msg="";
			}
			if(typeof width == "number"){
				width = width
			}else{
				width = 400;
			}
			var msgBoxHtml = "<div id='"+divid+"' title='说明'><p>"+msg+"</p></div>";
			jQuery("body").append(msgBoxHtml);
			jQuery('#'+divid).dialog({
				width: width,
				modal:true,
				close:function(){
					jQuery('#'+divid).remove();
				},
				buttons: {
					"确定": function() { 
						jQuery('#'+divid).remove();
						jQuery('#'+divid).dialog("destroy"); 
					}
				}
			});
			jQuery('#'+divid).dialog('open');
		};
		
		/**
		 *	模拟浏览器confirm消息确认框
		 *  参数：id 消息内容 width:消息框宽度，可不填
		 */
		package.confirm = function(msg,callback,width,para){
			var divid = "dialog" + parseInt(10000000*Math.random());
			var func;
			//参数检查
			if(typeof msg == "undefined"){
				msg="";
			}
			if(typeof callback == "function"){
				func = callback;
			}
			if(typeof width == "number"){
				width = width
			}else{
				width = 400;
			}
			var msgBoxHtml = "<div id='"+divid+"' title='确认框'><p>"+msg+"</p></div>";
			jQuery("body").append(msgBoxHtml);
			jQuery('#'+divid).dialog({
				width: width,
				modal:true,
				para:para,
				close:function(){
					jQuery('#'+divid).remove();
				},
				buttons: {
					"确定": function() {
							//alert(para.msg);
						if(typeof func !="undefined"){
							func.call(this,para);
						}
						jQuery('#'+divid).remove();
						jQuery('#'+divid).dialog("close"); 
					},
					"取消": function() {
						jQuery('#'+divid).remove();
						jQuery('#'+divid).dialog("close"); 
					}
				}
			});
			jQuery('#'+divid).dialog('open');
			
		};
		/**
		 *	以弹出框方式弹出本页面的div
		 *  参数：id 消息内容 width:消息框宽度，可不填
		 */
		package.showDivBoxWithoutHeader = function(id,width,height){
			var obj = package.showDivBox(id,width,height);
			obj.parent().find("div:first").remove();
			return obj;
		};
		/**
		 *	以弹出框方式弹出本页面的div
		 *  参数：id 消息内容 width:消息框宽度，可不填
		 */
		package.showDivBox = function(id,width,height){
			var obj;
			if(typeof id == "string"){
				obj = jQuery("#"+id);
			}else if(typeof id == "object"){
				obj = id;
			}else{
				return false;	
			}
			//宽度
			if(typeof width == "number"){
				width = width
			}else{
				width = "auto";
			}
			//高度
			if(typeof height == "number"){
				height = height
			}else{
				height = "auto";
			}
			obj.dialog({
				width: width,
				height:height,
				modal:true
			});
			obj.dialog("open");
			obj.close = function(func){
				if(typeof func == "function"){
					func.apply(this,arguments);
				}
				obj.dialog("close");
			};
			obj.open = function(func){
				obj.dialog("open")
			};
			//动态设置标题
			obj.setTitle = function(title){
				if(typeof title != "undefined"){
					obj.parent("div").find(".ui-dialog-title").html(title);
				}
			};
			return obj;
		};
		/**
		 *	载入参数为URL的弹出框
		 *  参数：id 消息内容 width:消息框宽度，可不填
		 */
		package.showUrlBoxWithoutHeader = function(url,title,width,height){
			var obj = package.showUrlBox(url,title,width,height);
			obj.parent().find("div:first").remove();
			return obj;
		};
		/**
		 *	载入参数为URL的弹出框
		 *  参数：id 消息内容 width:消息框宽度，可不填
		 */
		package.showUrlBox = function(url,title,width,height){
			var divid = "dialog" + parseInt(10000000*Math.random());
			var obj;		
			if(typeof width == "number"){
				width = width;
			}else{
				width = "auto";
			}
			if(typeof title == "undefined"){
				title = "";
			}
			var msgBoxHtml = "<div id='"+divid+"' title='"+title+"'><iframe src='"+url+"' width='"+(width-20)+"' height='"+height+"' frameborder='0' style='overflow-x:hidden'></iframe></div>";
			jQuery("body").append(msgBoxHtml);
			obj = jQuery('#'+divid);
			obj.dialog({
				width: width,
				modal:true,
				close:function(){
					obj.remove();
				}
			});
			obj.close = function(){
				obj.dialog("close");
			};
			
			//动态设置标题
			obj.setTitle = function(title){
				if(typeof title != "undefined"){
					obj.parent("div").find(".ui-dialog-title").html(title);
				}
			};
			obj.dialog('open');
			return obj;
		};
		
		/**
		 *	日期框
		 *  参数：id 消息内容 width:消息框宽度，可不填
		 */
		package.datepicker = function(id,para){
			var obj;
			if(typeof id == "string"){
				obj = jQuery("#id");
			}else if(typeof id == "object"){
				obj = jQuery(id);
			}else{
				return false;	
			}
			var jsonb;
			if(typeof para == "undefined"){
				jsonb = {inline: true,dateFormat: 'yy-mm-dd'};	
			}else if(typeof para == "object"){
				jsonb = jQuery.extend(para,{dateFormat: 'yy-mm-dd'});	
			}
			obj.datepicker(jsonb);
			return false;
		};
		
		/**
		 *	模拟浏览器alert弹出消息框
		 *  参数：msg 消息内容 width:消息框宽度，可不填
		 */
		package.tip = function(msg,width){
			var obj;
			if(typeof msg == "undefined"){
				msg="";
			}
			if(typeof width == "number"){
				width = width;
			}else{
				width = 400;
			}
			var msgBoxHtml = "<div id='dialog11212234749'><p>"+msg+"</p></div>";
			jQuery("body").append(msgBoxHtml);
			obj = jQuery('#dialog11212234749');
			obj.dialog({
				width: width,
				height:90,
				modal:true
			});
			obj.parent().find("div:first").remove();
			obj.dialog('open');
			obj.close = function(){
				obj.dialog("close");
			};
			obj.setText = function(text){
				obj.html("<p>"+text+"</p>");
			};
			return obj;
		};
		
		/**
		 *	等待
		 *  参数：msg 消息内容 width:消息框宽度，可不填
		 */
		package.loading = function(){
			var obj;
			var msgBoxHtml = "<div id='dialog112122347491111'></div>";
			jQuery("body").append(msgBoxHtml);
			obj = jQuery('#dialog112122347491111');
			obj.dialog({
				modal:true
			});
			obj.parent().find("div:first").remove();
			obj.parent("div").removeClass("ui-widget-content");
			obj.removeClass();
			obj.removeAttr("style");
			obj.addClass("loading");
			obj.dialog('open');
			obj.close = function(){
				obj.dialog("close");
			};
			return obj;
		};
	}
)(mf.msg);