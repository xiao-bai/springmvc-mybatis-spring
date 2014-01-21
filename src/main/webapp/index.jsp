<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css" />
  </head>
 
  <body>
    <form action="fileController/fileUpload.do" method="post" enctype="multipart/form-data">  
<input type="file" name="file" /> <input type="submit" value="Submit" /></form>

<a href="fileController/fileDownload.do">(下载文件)</a>

<button onclick="alert('xiaobai');" ></button>

<ul class="nav nav-pills">
  <li class="active">
    <a onclick="alert('sf');" href="#">上传前判断</a>
  </li>
  <li><a href="#">测试</a></li>
  <li><a href="#">test</a></li>
</ul>

<input type="file" id="files" name="files" multiple="" >
<div id="list">456</div>
  </body>
  
  <script type="text/javascript">
  (function () {
	  var file_id = 1, drop_zone;
	  function handle_file_select(event) {
	      event.stopPropagation();
	      event.preventDefault();
	      var i, output, files, file, workers, worker;
	      files = event.dataTransfer ? event.dataTransfer.files : event.target.files;
	      output = [];
	      for (i = 0; i < files.length; i += 1) {
	          file = files[i];
	          workers = [];
	      
	      
	      output.push('<tr>', '<td>MD5</td><td> <div class="progress" style="margin-bottom: 0px" id="md5_file_hash_', file_id, '"><div class="bar" style="width: 0%;"></div></div></td></tr>');
          worker = new Worker('resources/js/calculator/calculator.worker.md5.js');
          worker.addEventListener('message', handle_worker_event('md5_file_hash_' + file_id));
         // alert("1  "+file_id);
          workers.push(worker);
          hash_file(file, workers);
          alert(output);
          document.getElementById('list').innerHTML = '<table class="table">' + output.join('') + '</table>' + document.getElementById('list').innerHTML;
          alert(i+ output+ files+ file+ workers+ worker);
          }
	  }
	  
	  function handle_worker_event(id) {
	      return function (event) {
	        if (event.data.result) {
	          $("#" + id).parent().html(event.data.result);
	        } else {
	          $("#" + id + ' .bar').css('width', event.data.block.end * 100 / event.data.block.file_size + '%');
	        }
	      };
	    }
	  
	  
	  function hash_file(file, workers) {
		  //alert('sdf');
	      var i, buffer_size, block, threads, reader, blob, handle_hash_block, handle_load_block;

	      handle_load_block = function (event) {
	        for( i = 0; i < workers.length; i += 1) {
	          threads += 1;
	          //alert(event.target.result);
	          workers[i].postMessage({
	            'message' : event.target.result,
	            'block' : block
	          });
	        }
	      };
	      handle_hash_block = function (event) {
	        threads -= 1;

	        if(threads === 0) {
	          if(block.end !== file.size) {
	            block.start += buffer_size;
	            block.end += buffer_size;

	            if(block.end > file.size) {
	              block.end = file.size;
	            }
	            reader = new FileReader();
	            reader.onload = handle_load_block;
	            blob = file.slice(block.start, block.end);

	            reader.readAsArrayBuffer(blob);
	          }
	        }
	      };
	      buffer_size = 64 * 16 * 1024;
	      block = {
	        'file_size' : file.size,
	        'start' : 0
	      };

	      block.end = buffer_size > file.size ? file.size : buffer_size;
	      threads = 0;

	      for (i = 0; i < workers.length; i += 1) {
	        workers[i].addEventListener('message', handle_hash_block);
	      }
	      reader = new FileReader();
	      reader.onload = handle_load_block;
	      blob = file.slice(block.start, block.end);

	      reader.readAsArrayBuffer(blob);
	    }
	  
	  document.getElementById('files').addEventListener('change', handle_file_select, false);
  }());
  
  </script>
</html>
