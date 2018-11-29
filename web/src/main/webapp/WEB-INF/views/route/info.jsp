<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/route" />
<h4 class="header">ROUTE INFO</h4>

<div class="row">

	<div class="col s12">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					Name <span class="badge"><i class="material-icons">assignment</i></span>
				</div>
				<div class="collapsible-body">
					<p>"${formModel.name}"</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Route <span class="badge"><i class="material-icons">announcement</i></span>
				</div>
				<div class="collapsible-body">
					<div id="map" style="width: 100%; height: 300px"></div>
				</div>
			</li>
		</ul>
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
				zoom : 12
			}, {
				searchControlProvider : 'yandex#search'
			});

			// Создаем ломаную, используя класс GeoObject.
			var myGeoObject = new ymaps.GeoObject({
				// Описываем геометрию геообъекта.
				geometry : {
					// Тип геометрии - "Ломаная линия".
					type : "LineString",
					// Указываем координаты вершин ломаной.
					coordinates : points
				},
				// Описываем свойства геообъекта.
				properties : {
					// Содержимое хинта.
					hintContent : "I route",
					// Содержимое балуна.
					balloonContent : "Look map"
				}
			}, {
				// Задаем опции геообъекта.
				// Включаем возможность перетаскивания ломаной.
				draggable : false,
				// Цвет линии.
				strokeColor : "#FF0000",
				// Ширина линии.
				strokeWidth : 5
			});

			/* 	// Создаем ломаную с помощью вспомогательного класса Polyline.
				var myPolyline = new ymaps.Polyline([
				// Указываем координаты вершин ломаной.
				[ 55.80, 37.50 ], [ 55.80, 37.40 ],
						[ 55.70, 37.50 ], [ 55.70, 37.40 ] ], {
					// Описываем свойства геообъекта.
					// Содержимое балуна.
					balloonContent : "Ломаная линия"
				}, {
					// Задаем опции геообъекта.
					// Отключаем кнопку закрытия балуна.
					balloonCloseButton : false,
					// Цвет линии.
					strokeColor : "#000000",
					// Ширина линии.
					strokeWidth : 4,
					// Коэффициент прозрачности.
					strokeOpacity : 0.5
				}); */

			// Добавляем линии на карту.
			myMap.geoObjects.add(myGeoObject);

		});

	});
</script>


