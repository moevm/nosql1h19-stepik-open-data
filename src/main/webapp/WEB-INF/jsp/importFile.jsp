<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>Import/export</title>
    <script src=../../resources/js/importFile.js type="text/javascript"></script>
    <link href="<c:url value='../../resources/css/importFile.css' />" rel="stylesheet">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div align="center">
   <div align="center" class="choose">
        <h3 align="center">Import</h3>
        <form method="POST" action="/upload" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit" value="Submit"/>
        </form>
    </div>

    <div align="center" class="choose">
        <h3 align="center">Export</h3>
        <select id="selectBox" name="dropdown">
            <c:forEach var="item" items="${listOfCourses}">
                <option value=${item}>${item}</option>
            </c:forEach>
        </select>
        <button onclick="downloadCourse()">View</button>
    </div>
</div>
</body>
</html>
