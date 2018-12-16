<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/route" />
<h4 class="header">ROUTE INFO</h4>

<div class="row">

	<div class="col s12">
		<div id="map" style="width: 100%; height: 400px"></div>
		<div id="chart_lines" style="width: 100%; height: 500px;"></div>
	</div>
</div>

<div class="row">
	<div class="col s5"></div>

	<div class="col s3">
		<a class="waves-effect waves-light btn" href="${baseUrl}">Back<i
			class="material-icons right">undo</i>
		</a>
	</div>

	<div class="col s5"></div>
</div>

<script>
	var contextUrl = '${contextPath}';
	var routeId = '${formModel.id}';

	ymaps.ready(function() {
		$.get(contextUrl + "/route/points?routeId=" + routeId, function(
				routeData) {

			var points = [];
			var count = 0;
			var latSumm = 0;
			var longSumm = 0;
			var pointsData = routeData.points;
			var routeName = routeData.name;
			
			pointsData.forEach(function(p) {
				count++;
				var lat = p.latitude;
				var lon = p.longitude;
				latSumm += lat;
				longSumm += lon;
				points.push([ lat, lon ]);
			});

			var avgLat = latSumm / count;
			var avgLong = longSumm / count;
			// debugger;

			var myMap = new ymaps.Map("map", {
				center : [ avgLat, avgLong ],
				zoom : 14
			}, {
				searchControlProvider : 'yandex#search'
			});

			var startPoint = new ymaps.Placemark(points[0], {
				hintContent : "start route"
			}, {
				preset : 'islands#greenIcon'
			});
			myMap.geoObjects.add(startPoint);
			var finishPoint = new ymaps.Placemark(points[points.length - 1], {
				hintContent : "finish route"
			}, {
				preset : 'islands#redIcon'
			});
			myMap.geoObjects.add(finishPoint);

			var myGeoObject = new ymaps.GeoObject({
				geometry : {
					type : "LineString",
					coordinates : points
				},
				properties : {
					hintContent : routeName,
				
				}
			}, {
				draggable : false,
				strokeColor : "#FF0000",
				strokeWidth : 5
			});
			myMap.geoObjects.add(myGeoObject);
		});
	});
</script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		$.get(contextUrl + "/route/speed?routeId=" + routeId, function(
				speedIntervalData) {

			var points = [];
			var summ = 0;
			var dist = 0;
			speedIntervalData.forEach(function(p) {

				var distance = p.distance * 1000;
				var time = p.diffTime;
				dist += distance;
				summ += time;
				if (time == 0) {
					points.push([ dist, 0 ]);
				} else {
					speed = distance / time;
					points.push([ dist, speed ])
				}
			})

			var data = new google.visualization.DataTable();
			data.addColumn('number', 'distance');
			data.addColumn('number', 'values m/sec');
			data.addRows(points);

			var options_lines = {
				title : 'distance(m)/speed m/sec',
				curveType : 'function',
				lineWidth : 4,
				intervals : {
					'style' : 'line'
				},
				legend : 'none'
			};

			var chart_lines = new google.visualization.LineChart(document
					.getElementById('chart_lines'));
			chart_lines.draw(data, options_lines);

		})

	}
</script>


