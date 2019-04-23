<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>Import/export</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<form method="POST" action="/upload"  enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
