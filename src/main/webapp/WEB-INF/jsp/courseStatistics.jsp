<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>${courseName}</title>
    <link href="<c:url value='../../resources/css/courseStatistics.css' />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script>
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawChart1);
        google.charts.setOnLoadCallback(drawChart2);
        google.charts.setOnLoadCallback(drawChart3);


        var comments = [
            <c:forEach items="${comments}" var="hero">
            '<c:out value="${hero}" />',
            </c:forEach>
        ];

        var attempts = [
            <c:forEach items="${attempts}" var="hero">
            '<c:out value="${hero}" />',
            </c:forEach>
        ];

        var modules = [
            <c:forEach items="${modules}" var="hero">
            '<c:out value="${hero}" />',
            </c:forEach>
        ];
        var size = comments.length;

        function drawChart1() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Module');
            data.addColumn('number', 'Comments');
            data.addColumn({type: 'string', role: 'annotation'});
            data.addColumn('number', 'Attempts');
            data.addColumn({type: 'string', role: 'annotation'});

            for (var i = 0; i < size; i++) {
                data.addRows([
                    [modules[i], parseInt(comments[i]), comments[i], parseInt(attempts[i]), attempts[i]],
                ]);
            }

            var options = {
                title: 'Statistics for ${courseName} ',
                annotations: {
                    alwaysOutside: true,
                    textStyle: {
                        fontSize: 14,
                        color: '#000',
                        auraColor: 'none'
                    }
                },
                hAxis: {
                    title: 'Lessons'
                },
                vAxis: {
                    title: 'Comments and attempts'
                }
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('chart1'));
            chart.draw(data, options);
        }

        function drawChart2() {

            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Module');
            data.addColumn('number', 'Comments');
            data.addColumn({type: 'string', role: 'annotation'});

            var options = {
                title: 'Statistics of comments in lessons for ${courseName}',
                curveType: 'function',
                legend: {position: 'bottom'},
                hAxis: {
                    title: 'Lessons',

                },
                vAxis: {
                    title: 'Comments'
                }
            };
            for (var i = 0; i < size; i++) {
                data.addRows([
                    [modules[i], parseInt(comments[i]), comments[i]],
                ]);
            }

            var chart = new google.visualization.LineChart(document.getElementById('chart2'));

            chart.draw(data, options);
        }

        function drawChart3() {
            // Define the chart to be drawn.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Element');
            data.addColumn('number', 'Percentage');

            for (var i = 0; i < size; i++) {
                data.addRows([
                    [modules[i], parseInt(attempts[i])],
                ]);
            }

            var options = {
                title: 'Statistics of attempts in lessons for ${courseName}',
                annotations: {
                    alwaysOutside: true,
                    textStyle: {
                        fontSize: 20,
                        color: '#000',
                        auraColor: 'none'
                    }
                },
                hAxis: {
                    title: 'Modules'
                },
                vAxis: {
                    title: 'Attempts'
                },
                height: 600
            };

            // Instantiate and draw the chart.
            var chart = new google.visualization.PieChart(document.getElementById('chart3'));
            chart.draw(data, options);
        }

        function getStatistics() {
            var selectBox = document.getElementById("selectBox");
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            window.location = "/statistics" + "?courseName=" + selectedValue;
        }


        function getUserStatistics() {
            var selectBox = document.getElementById("selectBox2");
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            window.location = "/personalStatistics" + "?userName=" + selectedValue + "&courseName=${courseName}";
        }
    </script>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div align="center">
    <div align="center" id="courseWrapper" class="choose">
        <h3>Select course</h3>
        <select id="selectBox" name="dropdown">
            <c:forEach var="item" items="${listOfCourses}">
                <option value=${item}>${item}</option>
            </c:forEach>
        </select>
        <button onclick="getStatistics()">View</button>
    </div>


    <div align="center" id="userWrapper" class="choose">
        <h3>Select user</h3>
        <select id="selectBox2" name="dropdown2">
            <c:forEach var="item" items="${listOfUsers}">
                <option value=${item}>${item}</option>
            </c:forEach>
        </select>
        <button onclick="getUserStatistics()">View</button>
    </div>
</div>
<div id="chart1"></div>
<div id="chart2"></div>
<div id="chart3"></div>
</body>
</html>
