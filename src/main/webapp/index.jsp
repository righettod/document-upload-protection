<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>POC</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
		Select file to upload: <input type="file" name="fileToUpload" id="fileToUpload"> <input type="submit" value="Upload Image" name="submit">
	</form>
</body>
</html>
