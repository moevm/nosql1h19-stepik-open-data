<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>Statistics</title>
    <link href="<c:url value='../../resources/css/index.css' />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src=../../resources/js/index.js type="text/javascript"></script>
</head>

<body>
<jsp:include page="navbar.jsp"/>
<div align="center">
    <div class="mainWrapper">
    <h3>Select course</h3>
    <select id="selectBox" name="dropdown">
        <c:forEach var="item" items="${listOfCourses}">
            <option value=${item}>${item}</option>
        </c:forEach>
    </select>
    <button onclick="getStatistics()">View</button>
    </div>
</div>

</body>
</html>
