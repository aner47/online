// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ // author: meizz
  var o = {   
    "M+" : this.getMonth()+1,                 // 月份
    "d+" : this.getDate(),                    // 日
    "h+" : this.getHours(),                   // 小时
    "m+" : this.getMinutes(),                 // 分
    "s+" : this.getSeconds(),                 // 秒
    "q+" : Math.floor((this.getMonth()+3)/3), // 季度
    "S"  : this.getMilliseconds()             // 毫秒
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 	

$.fn.extend({
			editor: function(options) {
				window.UEDITOR_CONFIG = {
					UEDITOR_HOME_URL: getContextPath ()+'/resources/js/ueditor/',
					serverUrl: getContextPath ()+'/common/file/upload.jhtml',
					imageActionName: "uploadImage",
					imageFieldName: "file",
					imageUrlPrefix: "",
					imageMaxSize:10485760,
					imageAllowFiles:['.jpg', '.jpeg', '.bmp', '.gif', '.png'],
					imageCompressBorder: 1600,
					imagePathFormat: "",
					imageCompressEnable: false,
					imageCompressBorder: 1600,
					imageInsertAlign: "none",
					videoActionName: "uploadMedia",
					videoFieldName: "file",
					videoUrlPrefix: "",
					videoPathFormat: "",
					fileActionName: "uploadFile",
					fileFieldName: "file",
					fileMaxSize: 10485760,
					fileAllowFiles: ['.zip', '.rar', '.7z', '.doc', '.docx', '.xls', '.xlsx', '.ppt', '.pptx'],
					lang: 'zh_CN',
					fileUrlPrefix: "",
					filePathFormat: "",
					toolbars: [[
						'fullscreen', 'source', '|',
						'undo', 'redo', '|',
						'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|',
						'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
						'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
						'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
						'directionalityltr', 'directionalityrtl', 'indent', '|',
						'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
						'touppercase', 'tolowercase', '|',
						'link', 'unlink', 'anchor', '|',
						'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
						'insertimage', 'insertvideo', 'attachment', 'map', 'insertframe', 'pagebreak', '|',
						'horizontal', 'date', 'time', 'spechars', '|',
						'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
						'print', 'preview', 'searchreplace', 'drafts'
					]],
					iframeCssUrl: null,
					pageBreakTag: 'shopxx_page_break_tag',
					wordCount: false
				};
				
				UE.Editor.prototype.getActionUrl = function(action) {
					var serverUrl = this.getOpt('serverUrl');
					switch(action) {
						case "uploadImage":
							return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=image';
						case "uploadMedia":
							return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=media';
						case "uploadFile":
							return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=file';
					}
					return null;
				};
				
				UE.Editor.prototype.loadServerConfig = function() {
					this._serverConfigLoaded = true;
				};
				
				return this.each(function() {
					var element = this;
					var $element = $(element);
					
					UE.getEditor($element.attr("id"), options).ready(function() {
						this.execCommand("serverparam", {
						});
					});
				});
			}
		})
		
function getContextPath () {
	    var currentPath = window.document.location.href;
	    var pathName = window.document.location.pathname;
	    var pos = currentPath.indexOf(pathName);
	    var localhostPath = currentPath.substring(0, pos);
	    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	// alert("currentPath:" + currentPath + "\n"
	// + "pathName:" + pathName + "\n"
	// + "localhostPath:" + localhostPath + "\n"
	// + "projectName:" + projectName + "\n"
	// + "contextPath:"+ localhostPath + projectName);
	    return(localhostPath + projectName);
	}

function loadJs(file){
    var head = document.getElementsByTagName('head').item(0);
    var script = document.createElement('script');
    script.src = file;
    script.type = 'text/javascript';
    head.appendChild(script);
}


function loadCss(file){
    var head = document.getElementsByTagName('head').item(0);
    var css = document.createElement('link');
    css.href = file;
    css.rel = 'stylesheet';
    css.type = 'text/css';
    head.appendChild(css);
}