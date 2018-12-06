<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">
	<mytaglib:i18n key="map.info" />
	: ${formModel.name}
</h4>

<div class="row">
	<div class="col s12">
		<div>
			<script>
			
						ymaps.ready(initMapWithImage.bind(null,
									'${formModel.path}',
									${formModel.latitude1},
									${formModel.longitude1},
									${formModel.latitude2},
									${formModel.longitude2}));
						</script>
			<div id="map1" style="width: 100%; height: 500px"></div>
		</div>
	</div>
</div>



<div class="row">

	<div class="col s4">
		<sec:authorize access="!isAnonymous()">
			<form:form class="col s12"
				action="${baseUrl}/addRouteToMap/${formModel.id}"
				modelAttribute="idHolder" method="post"
				enctype="multipart/form-data">
				<div class="row">
					<div class="col s2"></div>
					<div class="col s8">
						<mytaglib:i18n key="map.add.route" />
						<form:select path="id">
							<option value="" disabled selected><mytaglib:i18n
									key="map.routes" />
								<form:options items="${myRoutes}" />
						</form:select>
						<form:errors path="id" cssClass="red-text" />
						<label for="id"><mytaglib:i18n key="map.routes" /></label>

					</div>
					<div class="col s2"></div>
				</div>
				<div class="row">
					<div class="col s2"></div>
					<div class="col s8">
						<input type="submit" value="<mytaglib:i18n key="map.add.route" />" />
					</div>
					<div class="col s2"></div>


				</div>
			</form:form>

		</sec:authorize>
	</div>
	<div class="col s4">
		<sec:authorize access="!isAnonymous()">
			<form:form class="col s12"
				action="${baseUrl}/deleteRouteFromMap/${formModel.id}"
				modelAttribute="idHolder" method="get"
				enctype="multipart/form-data">
				<div class="row">
					<div class="col s2"></div>
					<div class="col s8">
						<mytaglib:i18n key="map.delete.route" />
						<form:select path="id">
							<option value="" disabled selected><mytaglib:i18n
									key="map.routes" />
								<form:options items="${myRoutesOnMap}" />
						</form:select>
						<form:errors path="id" cssClass="red-text" />
						<label for="id"><mytaglib:i18n key="map.routes" /></label>

					</div>
					<div class="col s2"></div>
				</div>
				<div class="row">
					<div class="col s2"></div>
					<div class="col s8">
						<input type="submit"
							value="<mytaglib:i18n key="map.delete.route" />" />
					</div>
					<div class="col s2"></div>


				</div>
			</form:form>
		</sec:authorize>
	</div>


	<div class="col s4">

		<form:form class="col s12" action="#" modelAttribute="idHolder"
			method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					<mytaglib:i18n key="map.show" />
					<form:select path="id" cssClass="route-selector">
						<option value="" disabled selected><mytaglib:i18n
								key="map.routes" />
							<form:options items="${mapRoutes}" />
					</form:select>
					<label for="id"><mytaglib:i18n key="map.routes" /></label>
				</div>
				<div class="col s2"></div>
			</div>
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					<button type="button" onclick="showSelectedTrack()">
						<mytaglib:i18n key="map.show" />
					</button>
				</div>
				<div class="col s2"></div>
			</div>
		</form:form>
	</div>
</div>



<div class="row">
	<div class="col s5"></div>
	<div class="col s2">
		<a class="waves-effect waves-light btn" href="${baseUrl}"><mytaglib:i18n
				key="back" /><i class="material-icons right">undo</i> </a>
	</div>
	<div class="col s5"></div>
</div>



<script type="text/javascript">


function showSelectedTrack(){
	
	var selectedRouteId=$( "select.route-selector" ).val();
	//alert ('show selected track:'+selectedRoute);
	
	/* 
	1)need take routeId 
	2) init function, take pointsData
	3) create myGeoObject (route for current routeId)
	4) add myGeoObject to current map (id="map1" line 33)
	 */
 var contextUrl = '${contextPath}';
var routeId = '${formModel.id}';

	$.get(contextUrl + "/route/points?routeId=" + selectedRouteId, function(
			pointsData) {

		var points = [];
		
		pointsData.forEach(function(p) {
			var lat = p.latitude;
			var lon = p.longitude;
			points.push([ lat, lon ]);
		})

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
		
		debugger;
		globalMapReference.geoObjects.add(myGeoObject);   
	});
}
	 
</script>





