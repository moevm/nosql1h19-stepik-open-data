<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>PieChart</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
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
                title: 'Statistics of attempts in lessons ',
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
<div id="myPieChart"/>
</body>
</html>
