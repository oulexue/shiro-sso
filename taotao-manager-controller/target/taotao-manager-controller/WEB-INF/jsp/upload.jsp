<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'upload.jsp' starting page</title>
  </head>
  <script type="text/javascript">
  var UPLOADEDITOR = {
		// 编辑器参数
		kingEditorParams : {
			//指定上传文件参数名称
			filePostName  : "uploadFile",
			//指定上传文件请求的url。
			uploadJson : '/pic/upload',
			//上传类型，分别为image、flash、media、file
			dir : "image"
		}
  }
  function openUploadDialog(){
	  KindEditor.editor(UPLOADEDITOR.kingEditorParams).loadPlugin('image', function() {
			this.plugin.imageDialog({
				showRemote : false,
				clickFn : function(url, title, width, height, border, align) {
					var input = _self.siblings("input");
					input.parent().find("img").remove();
					input.val(url);
					input.after("<a href='"+url+"' target='_blank'><img src='"+url+"' width='80' height='50'/></a>");
					this.hideDialog();
				}
			});
		});
  }
  </script>
  <body>
    	<input type="button" onclick="openUploadDialog()" value="上传">
  </body>
</html>
