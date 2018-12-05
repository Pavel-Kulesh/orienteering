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
				pointsData) {

			var points = [];
			var count = 0;

			var latSumm = 0;
			var longSumm = 0;
			pointsData.forEach(function(p) {
				count++;
				var lat = p.latitude;
				var lon = p.longitude;
				latSumm += lat;
				longSumm += lon;
				points.push([ lat, lon ]);
			})
			var avgLat = latSumm / count;
			var avgLong = longSumm / count;
			// debugger;

			var myMap = new ymaps.Map("map", {
				center : [ avgLat, avgLong ],
				zoom : 14
			}, {
				searchControlProvider : 'yandex#search'
			});

			var myGeoObject = new ymaps.GeoObject({
				geometry : {
					type : "LineString",
					coordinates : points
				},
				properties : {
					hintContent : "I route",
					balloonContent : "Look map"
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
					points.push([ summ, 0 ]);
				} else {
					speed = distance / time;
					points.push([ summ, speed ])
				}

			})

			var data = new google.visualization.DataTable();
			data.addColumn('number', 'x');
			data.addColumn('number', 'values m/sec');

			data.addRows(points);

			// The intervals data as narrow lines (useful for showing raw source data)
			var options_lines = {
				title : 'Line speed intervals, default m/sec',
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


