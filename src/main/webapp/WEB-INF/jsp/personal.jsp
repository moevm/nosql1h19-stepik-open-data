<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>Personal statistics</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawChart1);
        google.charts.setOnLoadCallback(drawChart2);
        google.charts.setOnLoadCallback(drawChart3);
        google.charts.setOnLoadCallback(drawChart4);
        google.charts.setOnLoadCallback(drawChart5);
        google.charts.setOnLoadCallback(drawChart6);

        function drawChart1() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Module');
            data.addColumn('number', 'Comments');
            data.addColumn({type: 'string', role: 'annotation'});
            data.addColumn('number', 'Attempts');
            data.addColumn({type: 'string', role: 'annotation'});

            var comments = [
                <c:forEach items="${Test_mod_attempts}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var attempts = [
                <c:forEach items="${Test_mod_comments}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var modules = [
                <c:forEach items="${Jmodules}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];
            var size = comments.length
            for (var i = 0; i < size; i++) {
                data.addRows([
                    [modules[i], parseInt(comments[i]), comments[i], parseInt(attempts[i]),  attempts[i]],
                ]);
            }

            var options = {
                title: 'Personal statistics for TestCourse ',
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

            var chart = new google.visualization.LineChart(document.getElementById('chart_test'));
            chart.draw(data, options);
        }

        function drawChart2() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Module');
            data.addColumn('number', 'Comments');
            data.addColumn({type: 'string', role: 'annotation'});
            data.addColumn('number', 'Attempts');
            data.addColumn({type: 'string', role: 'annotation'});

            var comments = [
                <c:forEach items="${Python_mod_attempts}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var attempts = [
                <c:forEach items="${Python_mod_comments}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var modules = [
                <c:forEach items="${python_modules}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];
            var size = comments.length
            for (var i = 0; i < size; i++) {
                if(parseInt(comments[i])>=1000){
                    data.addRows([
                        [modules[i], parseInt(comments[i]), Math.floor(parseInt(comments[i])/1000)+'K' , parseInt(attempts[i]),  Math.floor(parseInt(attempts[i])/1000)+'K'],
                    ]);
                }
                else {
                    data.addRows([
                        [modules[i], parseInt(comments[i]), comments[i], parseInt(attempts[i]), Math.floor(parseInt(attempts[i]) / 1000) + 'K'],
                    ]);
                }
            }

            var options = {
                title: 'Statistics for Programming on Python',
                curveType: 'function',
                annotations: {
                    alwaysOutside: true,
                    textStyle: {
                        fontSize: 14,
                        color: '#000',
                        auraColor: 'none'
                    }
                },
                hAxis: {
                    title: 'Lessons',

                },
                vAxis: {
                    title: 'Comments and attempts'
                },
                height: 550,
                width:900,
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_python'));
            chart.draw(data, options);
        }
        function drawChart3() {
            var comments = [
                <c:forEach items="${Python_mod_attempts}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var modules = [
                <c:forEach items="${python_modules}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Module');
            data.addColumn('number', 'Comments');
            data.addColumn({type: 'string', role: 'annotation'});

            var options = {
                title: 'Statistics of comments in lessons for Programming on Python',
                curveType: 'function',
                legend: {position: 'bottom'},
                hAxis: {
                    title: 'Lessons',

                },
                vAxis: {
                    title: 'Attempts'
                },
            };
            var size = modules.length
            for (var i = 0; i < size; i++) {
                data.addRows([
                    [modules[i], parseInt(comments[i]), comments[i]],
                ]);
            }

            var chart = new google.visualization.LineChart(document.getElementById('curve_python'));

            chart.draw(data, options);
        }

        function drawChart4() {
            // Define the chart to be drawn.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Element');
            data.addColumn('number', 'Percentage');

            var modules = [
                <c:forEach items="${Jmodules}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var attempts = [
                <c:forEach items="${Test_mod_attempts}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var sizeOfModules = modules.length
            for (var i = 0; i < sizeOfModules; i++) {
                data.addRows([
                    [modules[i], parseInt(attempts[i])],
                ]);
            }

            var options = {
                title: 'Statistics of attempts in lessons for TestCourse',
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
                }
            };

            // Instantiate and draw the chart.
            var chart = new google.visualization.PieChart(document.getElementById('chart2_test'));
            chart.draw(data, options);
        }
        function drawChart5() {
            // Define the chart to be drawn.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Element');
            data.addColumn('number', 'Percentage');

            var modules = [
                <c:forEach items="${literature_modules}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var attempts = [
                <c:forEach items="${literature_mod_attempts}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var sizeOfModules = modules.length
            for (var i = 0; i < sizeOfModules; i++) {
                data.addRows([
                    [modules[i], parseInt(attempts[i])],
                ]);
            }

            var options = {
                title: 'Statistics of attempts in lessons for Literature',
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
                }
            };

            // Instantiate and draw the chart.
            var chart = new google.visualization.LineChart(document.getElementById('chart1_literature'));
            chart.draw(data, options);
        }

        function drawChart6() {
            // Define the chart to be drawn.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Element');
            data.addColumn('number', 'Percentage');

            var modules = [
                <c:forEach items="${literature_modules}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var attempts = [
                <c:forEach items="${literature_mod_comments}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var sizeOfModules = modules.length
            for (var i = 0; i < sizeOfModules; i++) {
                data.addRows([
                    [modules[i], parseInt(attempts[i])],
                ]);
            }

            var options = {
                title: 'Statistics of attempts in lessons for Literature',
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
                    title: 'Comments'
                }
            };

            // Instantiate and draw the chart.
            var chart = new google.visualization.PieChart(document.getElementById('chart2_literature'));
            chart.draw(data, options);
        }

        function changeGraphics(){
            var selectBox = document.getElementById("selectBox");
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            alert(selectedValue);

            $(document).ready(function(){
                if(selectedValue == "TestCourse"){
                    $("#chart_python").hide();
                    $("#chart_test").show();
                    $("#curve_python").hide();
                    $("#chart2_test").show();
                    $("#chart1_literature").hide();
                    $("#chart2_literature").hide();
                }
                if (selectedValue == "Programming"){
                    $("#chart_test").hide();
                    $("#chart2_test").hide();
                    $("#curve_python").show();
                    $("#chart_python").show();
                    $("#chart1_literature").hide();
                    $("#chart2_literature").hide();
                }
                if (selectedValue == "Literature"){
                    $("#chart_test").hide();
                    $("#chart2_test").hide();
                    $("#curve_python").hide();
                    $("#chart_python").hide();
                    $("#chart1_literature").show();
                    $("#chart2_literature").show();
                }
            });
        }


    </script>
</head>

<body>
<jsp:include page="navbar.jsp"/>
<div align="center">
    <h1>Personal statistics</h1>
    <h3>Choose the graphic</h3>
    <select id="selectBox" name ="courseName" onchange="changeGraphics();" style>
        <c:forEach var="item" items="${listOfCourses}">
            <option value=${item}>${item}</option>
        </c:forEach>
    </select>
</div>
<br></br>

<div id="chart_test"></div>
<div id="chart_python"></div>
<div id="curve_python"></div>
<div id="chart2_test"></div>
<div id="chart1_literature"></div>
<div id="chart2_literature"></div>

</body>
</html>
