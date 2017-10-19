<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Boot - New Features</title>
</head>
<body>
Spring Boot - New Features
 <body>
      <h3>File Upload:</h3>
      Select a file to upload: <br />
      <form action = "uploadFile" method = "post"
         enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "50" />
         <br />
         <input type = "submit" value = "Upload File" />
      </form>
   </body>
</body>
</html> -->

<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Show File Data</title>
<style type='text/css'>
body {
    font-family: sans-serif;
}
</style>
<script type='text/javascript'>
function showFileSize() {
    var input, file;

    // (Can't use `typeof FileReader === "function"` because apparently
    // it comes back as "object" on some browsers. So just see if it's there
    // at all.)
    if (!window.FileReader) {
        bodyAppend("p", "The file API isn't supported on this browser yet.");
        return;
    }

    input = document.getElementById('file');
    if (!input) {
        bodyAppend("p", "Um, couldn't find the fileinput element.");
    }
    else if (!input.files) {
        bodyAppend("p", "This browser doesn't seem to support the `files` property of file inputs.");
    }
    else if (!input.files[0]) {
        bodyAppend("p", "Please select a file before clicking 'Load'");
    }
    else {
        file = input.files[0];
        bodyAppend("p", "File " + file.name + " is " + file.size + " bytes in size");
        if(file.size>10000){
        	alert("Can't upload the file. File Size is more than 10 MB");
        return;
        }
    }
    document.fileUpload.submit()
}

function bodyAppend(tagName, innerHTML) {
    var elm;

    elm = document.createElement(tagName);
    elm.innerHTML = innerHTML;
    document.body.appendChild(elm);
}
function bodyAppend(tagName, innerHTML) {
    var elm;

    elm = document.createElement(tagName);
    elm.innerHTML = innerHTML;
    document.body.appendChild(elm);
}
</script>
</head>
<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
 <form name= "fileUpload" action = "fileUpload" method = "post" enctype = "multipart/form-data">
<input type = "file" name = "file" id="file" size = "50" />
Description: 
 <input type="text" id="description" name="description" />
 UserId: 
  <input type="text" id="userId" name="userId" />
<input type='button' id='btnLoad' value='Load' onclick='showFileSize();'>
</form>
<br>
<br>
<br>
<c:if test="${!empty fileUpload}">
		
		<table border="1">
		<tr>
		<td>FileName
		</td>
		<td>Description
		</td><td>UploadedOn
		</td><td>UpdatedOn
		</td>
		</tr>
		<tr>
		<td>${fileUpload.fileName}
		</td>
		<td>${fileUpload.description}
		</td><td>${fileUpload.uploadedOn}
		</td><td>${fileUpload.updatedOn}
		</td>
		</tr>

	</c:if>
</body>
</html>