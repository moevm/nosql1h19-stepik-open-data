<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
    <link href="<c:url value="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"/>" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawAnnotations);

        function drawAnnotations() {
            var data = new google.visualization.DataTable();
            data.addColumn('timeofday', 'Time of Day');
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
                    [{v: [10 + i, 0, 0], f: modules[i]}, parseInt(comments[i]), comments[i], parseInt(attempts[i]),  attempts[i]],
                ]);
            }

            var options = {
                title: 'Statistics of attempts and comments in lessons ',
                annotations: {
                    alwaysOutside: true,
                    textStyle: {
                        fontSize: 14,
                        color: '#000',
                        auraColor: 'none'
                    }
                },
                hAxis: {
                    title: 'Modules'
                },
                vAxis: {
                    title: 'Comments and attempts'
                }
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<h2>Hello </h2>
<div id="chart_div"></div>
<div>
    ${comments}
</div>
</body>
</html>
