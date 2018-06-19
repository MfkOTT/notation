;(function( jQuery , win , undefined){

	jQuery.fn.xq_navbar = function( _option ){
		var defaults = {
			"bgcolor"	: "rgb(0,0,0,.5)",		//导航条颜色
			"type"		: "line",	//定义导航类型    下划线 underline 上划线overline 双划线line 块级背景block 
			"liwidth"	: "50",		//设置导航项的宽度类型 auto：自动  ， avg：评分， 30：指定具体宽度
			"hcolor"	: ["blue","rgb(10,100,100)","red","pink","green","rgba(23,234,22,1)","rgb(230,230,230)"]//指定每一个导航项的颜色。不指定或指定不够默认 #ccc；统一颜色可直接传入颜色值
		}
		var _self	=	jQuery(this);
		var xq_li	=	_self.find(".xq_navli");
		this.navbar	=	_self.find(".xq_navbar");
		this.num	=	_self.find(".xq_navli").length;
		this.width	=	this.navbar.width();
		this.height	=	this.navbar.height();
        // console.log(defaults.bgcolor)
		jQuery.extend( defaults , _option ? _option : {} );
		this.init	=	function(){
			// this.navbar.css({"background":defaults.bgcolor,"line-height":this.height+"px"});
			this.navbar.css({"background":"rgb(0,0,0,.1)","line-height":this.height+"px"});
            xq_li.css({"width":"25%",});
			// switch(defaults.liwidth){
			// 	case "auto":
			// 		xq_li.css({"padding":"0px 10px",});
			// 		break;
			// 	case "avg":
			// 		xq_li.css({"width":this.width/this.num+"px",});
			// 		break;
			// 	default:
			// 		// xq_li.css({"width":defaults.liwidth+"px",});
			// 		xq_li.css({"width":"50px",});
			// 		break;
			// }
			this.bindmouseover(defaults.type);
		}
		this.bindmouseover=function(type){
			switch(type){
				case "block":
					this.block();
					break;
				case "beat":
					this.beat();
					break;
				default :
					this.line();
					break;
			}
		}
		this.line=function(){
			var move=jQuery("<div/>",{"class":"xq_move"}).css({ "height":"5px","left":"-100px","width":"100px"}).appendTo(_self);
			switch(defaults.type){
				case "underline":
					move.css({"bottom":"0px"});
					break;
				case "overline":
					move.css({"top":"0px"});
					break;
				case "line":
					move.css({"top":"0px"});
					jQuery("<div/>",{"class":"xq_move"}).css({ "bottom":"0px","height":"5px","left":"-100px","width":"100px"}).appendTo(_self);
					break;
			}
			var width;
			var movelist=_self.find(".xq_move");
			xq_li.on('mouseover',function(){
				width=jQuery(this).width();
				if( defaults.liwidth == "auto" ){
					width	+=	20;
				}
				var left	=	jQuery(this).offset().left-_self.offset().left;
				var index	=	jQuery(this).index();
				var color	=	jQuery.isArray(defaults.hcolor) ? (defaults.hcolor[index] ? defaults.hcolor[index] : "#ccc") : jQuery.trim(defaults.hcolor);
				jQuery(this).css({"color":color});
				movelist.css({ "background" : color , "left" : left , "width" : width+"px"});
			});
			this.navbar.on('mouseout',function(){
				xq_li.css({"color":"#fff"});
				movelist.css({"left":"-100px","width":"100px"});
			});
		}
		this.block=function(){
			var background;
			xq_li.on('mouseover',function(){
				var index	=	jQuery(this).index();
				background	=	jQuery(this).css("background");
				var bgColor	=	jQuery.isArray(defaults.hcolor) ? (defaults.hcolor[index] ? defaults.hcolor[index] : "#ccc") : jQuery.trim(defaults.hcolor);
				jQuery(this).css("background",bgColor);
			});
			xq_li.on('mouseout',function(){
				jQuery(this).css("background",background);
			});
		}
		this.beat=function(){
			var animArr=["flash","leftdown","shake","bounce","bigsmall","centerspin","centerturn","runspin","xyspin"];
			xq_li.each(function(){
				jQuery(this).addClass("animated");
				var anima=animArr[Math.floor(Math.random()*animArr.length)];
				var background;
				jQuery(this).on('mouseover',function(){
					var index	=	jQuery(this).index();
					background	=	jQuery(this).css("background");
					var bgColor	=	jQuery.isArray(defaults.hcolor) ? (defaults.hcolor[index] ? defaults.hcolor[index] : "#ccc") : jQuery.trim(defaults.hcolor);
					jQuery(this).css("background",bgColor).addClass(anima);
				});
				jQuery(this).on('mouseout',function(){
					jQuery(this).css("background",background).removeClass(anima);
				});
			});
		}
		this.init();
	}
})(jQuery,window)