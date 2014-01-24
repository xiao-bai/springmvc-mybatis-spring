<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>搜索引擎主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />


</head>

<body style="height:5000px;">

		<div class="WB_global_nav">
			<form>
  				<input type="text" class="span2" style="height:30px;width:300px;margin-top:5px;">
  				<input type="button" class="mybtn" onclick="hotwordresult();" value="搜索一下" style="font-size:16px;width:95px;height:32px;marigin-left:30px;margin-top:5px;background-color:#ddd;cursor:pointer;" >
			</form>
			
		</div>
		<div class="my_content">
			<span>以下为内容</span>
		</div>

	<div class="s-ps-sug" >
		<table id="st" cellspacing="0" cellpadding="2">
			<tbody>
				<tr class="ml">
					<td><span> 你 </span> <b> 把我灌醉 </b></td>
				</tr>
				<tr class="ml">
					<td><span> 你 </span> <b> 为我着迷 </b></td>
				</tr>
				<tr class="ml">
					<td><span> 你 </span> <b> 是我的眼 </b></td>
				</tr>
				<tr class="ml">
					<td><span> 你 </span> <b> 是下一个 </b></td>
				</tr>
				<tr class="ml">
					<td><span> 你 </span> <b> 把我灌醉 邓紫棋 </b></td>
				</tr>
				<tr class="ml">
					<td><span> 你 </span> <b> 好 </b></td>
				</tr>
				<tr class="ml">
					<td><span> 你 </span> <b> 为我着迷电视剧 </b></td>
				</tr>
				
				
			</tbody>
		</table>
	</div>


	<script>
	function hotwordresult(){
		alert("开始搜索");
	}
	
	
	$(document).scroll(function() {
		$(".s-ps-sug").hide();	 
	});
	
	
	$(function(){
		 $(document).bind("click",function(e){
		  var target  = $(e.target);
		  if(target.closest(".s-ps-sug").length == 0&target.closest(".span2").length==0){
		       $(".s-ps-sug").hide();
		  }
		 });
		});
	
	
	
	$(".span2").bind("input propertychange", function() {
		findHotWord();
	});
	$(".ml").bind("click",function(){
		var content = $.trim($(this).find("span").html())+$.trim($(this).find("b").text());
		$(".span2").val(content);
		$(".s-ps-sug").hide();
		alert('开始后台查询');
	});
	function findHotWord() {
		var url = "hotWordController/find/" + $(".span2").val()+ ".do";
		//var data="{word:"+$('#productName').val()+"}";
			$.ajax({
				type : 'POST',
				url : url,
				dataType : "json",
				//data: data,
				success : function(msg) {
					var tbody = $("<tbody></tabody>");
					var tr ;
					var td ;
					for(var i = 0 ; i < msg.obj.length ; i ++ ){
						tr = $("<tr class='ml'></tr>");
						td = $("<td><span>"+msg.obj[0].prefix+"</span> <b>"+msg.obj[i].word +"</b></td>");
						tr.append(td);
						tbody.append(tr);
					}
					$("#st").html("");
					$("#st").append(tbody);
					$(".s-ps-sug").show();
					},
				error : function(msg) {
					alert("error");
					}
				});
	}
	</script>
</body>
</html>
