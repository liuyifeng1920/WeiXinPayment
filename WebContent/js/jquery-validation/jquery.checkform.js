jQuery.extend({
    //去除左边的空格
    ltrim: function(_str){
        return _str.replace(/(^\s*)/g, "");
    },
    //去除右边的空格
    rtrim: function(_str){
        return _str.replace(/(\s*$)/g, "");
    },
    //因为jquery本身已经有了trim方法,故这里不再重新定义
    //计算字符串长度，一个双字节字符长度计2，ASCII字符计1
    lengthw: function(_str){
        return _str.replace(/[^\x00-\xff]/g, "rr").length;
    },
    //转换为大写
    toUpper: function(_str){
        return _str.toUpperCase();
    },
    //转换为小写
    toLower: function(_str){
        return _str.toLowerCase();
    },
    //15位身份证转换为18位,如果参数字符串中有非数字字符,则返回"#"表示参数不正确
    idCardUpdate: function(_str){
        var idCard18;
        var regIDCard15 = /^(\d){15}$/;
        if (regIDCard15.test(_str)) {
            var nTemp = 0;
            var ArrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var ArrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            _str = _str.substr(0, 6) + '1' + '9' + _str.substr(6, _str.length - 6);
            for (var i = 0; i < _str.length; i++) {
                nTemp += parseInt(_str.substr(i, 1)) * ArrInt[i];
            }
            _str += ArrCh[nTemp % 11];
            idCard18 = _str;
        }
        else {
            idCard18 = "#";
        }
        return idCard18;
    },
    //是否为空字符串
    isEmpty: function(_str){
        var tmp_str = jQuery.trim(_str);
        return tmp_str.length > 0;
    },
    isChinese: function(_str){
        return /^[\u4E00-\u9FA5]{0,25}$/.test(_str);
    },
    isEnglish: function(_str){
    	return /^[A-Za-z]{0,25}$/.test(_str);
    },
    isNumber: function(_str){
    	return /^[\d|\.|,]+$/.test(_str);
    },
    isDate:function(_str){
    	return /^\d{2}\/\d{2}\/\d{4}$/.test(_str);
    },
    isTime: function(_str){
    	return /^\d{2}\/\d{2}\/\d{4}\s\d{2}:\d{2}:\d{2}$/.test(_str);
    },
    //是否为合法电子邮件地址
    isEmail: function(_str){
        return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(_str);
    },
    //是否合法url地址
    isURL: function(_str){
        var regTextUrl = /^(file|http|https|ftp|mms|telnet|news|wais|mailto):\/\/(.+)$/;
        return regTextUrl.test(_str);
    },
    //是否为合法ip地址
    isIpAddress: function(_str){
        if (_str.length == 0) 
            return (false);
        reVal = /^(\d{1}|\d{2}|[0-1]\d{2}|2[0-4]\d|25[0-5])\.(\d{1}|\d{2}|[0-1]\d{2}|2[0-4]\d|25[0-5])\.(\d{1}|\d{2}|[0-1]\d{2}|2[0-4]\d|25[0-5])\.(\d{1}|\d{2}|[0-1]\d{2}|2[0-4]\d|25[0-5])$/;
        return (reVal.test(_str));
    },
    //是否邮政编码(中国)
    isPostalCode: function(_str){
        var regTextPost = /^(\d){6}$/;
        return regTextPost.test(_str);
    },
    //是否是有效中国身份证
    isIDCard: function(_str){
        var iSum = 0;
        var info = "";
        var sId;
        var aCity = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外"
        };
        //如果输入的为15位数字,则先转换为18位身份证号
        if (_str.length == 15) 
            sId = jQuery.idCardUpdate(_str);
        else 
            sId = _str;
        
        if (!/^\d{17}(\d|x)$/i.test(sId)) {
            return false;
        }
        sId = sId.replace(/x$/i, "a");
        //非法地区
        if (aCity[parseInt(sId.substr(0, 2))] == null) {
            return false;
        }
        var sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
        var d = new Date(sBirthday.replace(/-/g, "/"))
        //非法生日
        if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
            return false;
        }
        for (var i = 17; i >= 0; i--) {
            iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
        }
        if (iSum % 11 != 1) {
            return false;
        }
        return true;
    },
    //是否有效的电话号码(中国)
    isPhoneCall: function(_str){
        return /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^[0-9]{3,4}[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/.test(_str);
    },
    //是否有效的手机号码(最新的手机号码段可以是15开头的
    isMobile: function(_str){
        return /^0{0,1}1(3|5)[0-9]{9}$/.test(_str);
    },
    //是否有效用户名,长度在6-50之间的，只包含字母，数字和下划线
    isValidUserName: function(_str){
        return /^\w{6,50}$/.test(_str);
    },
    //是否有效密码,长度在6-20之间
    isValidPass: function(_str){
        return /^\w{6,20}$/.test(_str);
    }
});
/**
 * 以上代码由龙的天空提供
 * 地址：http://www.cnblogs.com/longer/archive/2008/01/10/1033672.html
 */
/**
 * 表单验证插件
 */
$.fn.checkform=function(opt){
	var cfg={
		text:{
			'isNull':'',
			'isEmpty':'',
			'isChinese':'',
			'isURL':'',
			'isEmail':'',
			'isIpAddress':'',
			'isPostalCode':'',
			'isPhoneCall':'',
			'isMobile':'',
			'isValidUserName':'',
			'isValidPass':'',
			'isEnglish':'',
			'isDate':'',
			'isTime':'',
			'isNumber':''
		},
		radioorcheckbox:{
			'checkonly':''
		},
		advance:{
			'compare':'',
			'regexp':'',
			'function':''
		},
		errorClass:'error',
		msgClass:'noticeMsg',
		noticeClass:'noticeInput'
	};
	$.extend(cfg,opt);
	var form=$(this);
	var checkFlag=true;
	function checking(){
		form.find('input[type=text],textarea,select').each(function(){
			var _self=$(this);
			//做基础校验
			for(var i in cfg.text){
				var check=_self.attr(i);
				if(typeof(check)!='undefined'){
					try{
						if(check=='true'){
							checkFlag=$[i](_self.val());
						}
						else{
							checkFlag=eval('('+check+'("'+_self.val()+'"))');
						}
						if(!checkFlag){
							_self.addClass(cfg.errorClass);
							_self.unbind('click',function(){
								form.find('.'+cfg.msgClass).remove();
								$(this).removeClass(cfg.errorClass);
							}).bind('click',function(){
								form.find('.'+cfg.msgClass).remove();
								$(this).removeClass(cfg.errorClass);
							});
							//显示提示语言在输入信息的旁边
							form.find('.'+cfg.msgClass).remove();
							$('<span class="'+cfg.msgClass+'">'+_self.attr('msg')+'</span>').insertAfter(_self);
							return false;
						}
					}catch(error){}
				}
				else{
					continue;
				}
			}
			//高级验证
			for(var i in cfg.advance){
				var check=_self.attr(i);
				if(check){
					switch(i){
						//> < >= <= 四种格式
						//样例 compare=">=:test"
						case 'compare':
						var arr_check=check.split(':');
						var targ=form.find('input[name='+$.trim(arr_check[1])+']').val();
						checkFlag=eval('('+_self.val()+arr_check[0]+targ+')');
						break;
						//自定义正则
						case 'regexp':
						var re=new RegExp(check);
						checkFlag=re.test(_self.val());
						break;
						//自定义函数，要判断是定义匿名函数，还是执行函数
						case 'function':
						checkFlag=eval('('+check+'("'+_self.val()+'"))');
						break;
					}
					if(!checkFlag){
						_self.addClass(cfg.errorClass);
						_self.unbind('click',function(){
							form.find('.'+cfg.msgClass).remove();
							$(this).removeClass(cfg.errorClass);
						}).bind('click',function(){
							form.find('.'+cfg.msgClass).remove();
							$(this).removeClass(cfg.errorClass);
						});
						//显示提示语言在输入信息的旁边
						form.find('.'+cfg.msgClass).remove();
						$('<span class="'+cfg.msgClass+'">'+_self.attr('msg')+'</span>').insertAfter(_self);
						_self.focus();
						return false;
					}
				}
				else{
					continue;
				}
			}
		});
		form.find('input[type=radio],input[type=checkbox]').each(function(){
			var _self=$(this);
			//做基础校验
			for(var i in cfg.radioorcheckbox){
				var check=_self.attr(i);
				if(typeof(check)!='undefined'){
					try{
						if(check=='true'){
							//获得改组radio或者是checkbox
							var name=_self.attr('name');
							if(!form.find('input[name='+name+']:checked')){
								_self.addClass(cfg.errorClass);
								_self.unbind('click',function(){
									form.find('.'+cfg.msgClass).remove();
									$(this).removeClass(cfg.errorClass);
								}).bind('click',function(){
									form.find('.'+cfg.msgClass).remove();
									$(this).removeClass(cfg.errorClass);
								});
								//显示提示语言在输入信息的旁边
								form.find('.'+cfg.msgClass).remove();
								$('<span class="'+cfg.msgClass+'">'+_self.attr('msg')+'</span>').insertAfter(_self);
								_self.focus();
								return false;
							}
						}
					}catch(error){
					}
					//return true;
				}
				else{
					continue;
				}
			}
		});
		return checkFlag;
	}
	function initInput(){
		form.find('input[type=text],input[type=checkbox],input[type=radio],textarea,select').each(function(){
			var _self=$(this);
			_self.change(function(){
				checking();
			});
			var notice=_self.attr('notice');
			if(typeof(notice)!='undefined'){
				//显示提示语言在输入信息的旁边
				_self.focus(function(){
					form.find('.'+cfg.noticeClass).remove();
					$('<span class="'+cfg.noticeClass+'">'+notice+'</span>').insertAfter(_self);
				}).blur(function(){
					form.find('.'+cfg.noticeClass).remove();
				});
			}
		});
	}
	initInput();
	form.submit(function(){
		return checking();
	});
}