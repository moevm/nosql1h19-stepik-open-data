<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <jsp:include page="layout.jsp"/>
    <title>Statistics</title>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawChart1);
        google.charts.setOnLoadCallback(drawChart2);
        google.charts.setOnLoadCallback(drawChart3);
        google.charts.setOnLoadCallback(drawChart4);

        function drawChart1() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Module');
            data.addColumn('number', 'Comments');
            data.addColumn({type: 'string', role: 'annotation'});
            data.addColumn('number', 'Attempts');
            data.addColumn({type: 'string', role: 'annotation'});

            var comments = [
                <c:forEach items="${Jcomments}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var attempts = [
                <c:forEach items="${Jattempts}" var="hero">
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
                title: 'Statistics for TestCourse ',
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

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
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
                <c:forEach items="${python_comments}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var attempts = [
                <c:forEach items="${python_attempts}" var="hero">
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
                colors: ['#1b9e77', '#d95f02'],
                height: 750,
                width:1400,
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('chart2_div'));
            chart.draw(data, options);
        }
        function drawChart3() {
            var comments = [
                <c:forEach items="${Jcomments}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];

            var modules = [
                <c:forEach items="${Jmodules}" var="hero">
                '<c:out value="${hero}" />',
                </c:forEach>
            ];
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Module');
            data.addColumn('number', 'Comments');
            data.addColumn({type: 'string', role: 'annotation'});

            var options = {
                title: 'Statistics of comments in lessons for TestCourse',
                curveType: 'function',
                legend: {position: 'bottom'},
                hAxis: {
                    title: 'Lessons',

                },
                vAxis: {
                    title: 'Comments'
                },
            };
            var size = modules.length
            for (var i = 0; i < size; i++) {
                data.addRows([
                    [modules[i], parseInt(comments[i]), comments[i]],
                ]);
            }

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

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
                <c:forEach items="${Jattempts}" var="hero">
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
            var chart = new google.visualization.PieChart(document.getElementById('myPieChart'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div id="chart_div"></div>
<div id="chart2_div"></div>
<div id="curve_chart"></div>
<div id="myPieChart"></div>
</body>
</html>
