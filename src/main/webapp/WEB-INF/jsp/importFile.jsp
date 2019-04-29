<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>Import/export</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1 align="center">Import/export</h1>
<div style="position: fixed; top: 20%; left: 40%;">
    <form method="POST" action="/upload"  enctype="multipart/form-data" >
        <input type="file" name="file" /><br/><br/>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
