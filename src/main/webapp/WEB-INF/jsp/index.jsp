<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>Statistics</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        function getStatistics() {
            var selectBox = document.getElementById("selectBox");
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            window.location = "/statistics" + "?courseName=" + selectedValue;
        }

    </script>
</head>

<body>
<jsp:include page="navbar.jsp"/>
<div align="center">
    <h1>General statistics</h1>
    <h3>Choose the graphic</h3>
    <select id="selectBox" name="dropdown" onchange="changeGraphics();">
        <c:forEach var="item" items="${listOfCourses}">
            <option value=${item}>${item}</option>
        </c:forEach>
    </select>
    <button onclick="getStatistics()">View</button>
</div>

</body>
</html>
