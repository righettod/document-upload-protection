<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>POC</title>
</head>
<body>
	<form action="/upload" method="post" enctype="multipart/form-data">
		File format:
		<select id="fileType" name="fileType">
			<option value="WORD">Microsoft Office Word</option>
			<option value="EXCEL">Microsoft Office Excel</option>
			<option value="PDF">Adobe PDF</option>
			<option value="IMAGE">Image</option>
		</select><br>
		Select file to upload: <input type="file" name="fileContent" id="fileContent"><br><input type="submit" value="Upload file" name="submit">
	</form>
</body>
</html>
