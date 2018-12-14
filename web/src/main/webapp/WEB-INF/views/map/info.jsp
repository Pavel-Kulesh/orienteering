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
	<div class="col s6">
		block1
		<div class="row">
			<div class="col s6">
				<sec:authorize access="!isAnonymous()">
					<div class="row">
						1
						<mytaglib:i18n key="map.add.route" />
					</div>
					<div class="row">
						<form:form class="col s12"
							action="${baseUrl}/addRouteToMap/${formModel.id}"
							modelAttribute="idHolder" method="post">
							<div class="row">
								<div class="col s1"></div>

								<div class="col s10">
									<form:select path="id">
										<option value="" disabled selected><mytaglib:i18n
												key="map.routes" />
											<form:options items="${myRoutes}" />
									</form:select>
								</div>
								<div class="col s1"></div>
							</div>
							<div class="row">
								<div class="col s1"></div>
								<div class="col s10">
									<button class="btn waves-effect waves-light" type="submit">
										<mytaglib:i18n key="map.add.route" />
									</button>
								</div>
								<div class="col s1"></div>

							</div>
						</form:form>
					</div>
					<sec:authorize access="hasRole('ADMIN')">
						<div class="row">3</div>
					</sec:authorize>
				</sec:authorize>
			</div>
			<div class="col s6">
				<sec:authorize access="!isAnonymous()">
					<div class="row">
						4
						<mytaglib:i18n key="map.delete.route" />
					</div>
					<div class="row">
						<form:form class="col s12"
							action="${baseUrl}/deleteRouteFromMap/${formModel.id}"
							modelAttribute="idHolder" method="post">
							<div class="row">

								<div class="col s12">
									<form:select path="id">
										<option value="" disabled selected><mytaglib:i18n
												key="map.routes" />
											<form:options items="${myRoutesOnMap}" />
									</form:select>
								</div>

							</div>
							<div class="row">
								<div class="col s12">
									<button class="btn waves-effect waves-light red" type="submit">
										<mytaglib:i18n key="map.delete.route" />
									</button>

								</div>


							</div>
						</form:form>
					</div>
					<sec:authorize access="hasRole('ADMIN')">
						<div class="row">6</div>
					</sec:authorize>
				</sec:authorize>
			</div>
		</div>





	</div>
	<div class="col s6">
		block2
		<div class="row">
			<div class="col s6">
				<div class="row">
					1
					<mytaglib:i18n key="map.show.distance" />
				</div>
				<div class="row">
					2
					<form:form class="col s12" action="#" modelAttribute="idHolder"
						method="get">
						<div class="row">

							<div class="col s12">
								<form:select path="id" cssClass="dist-selector">
									<option value="" disabled selected><mytaglib:i18n
											key="map.routes" />
										<form:options items="${mapRoutes}" />
								</form:select>
								<label for="id"><mytaglib:i18n key="map.routes" /></label>
							</div>

						</div>
						<div class="row">

							<div class="col s12">
								<a class="waves-effect waves-light btn"
									onclick="showSelectedDistance()"> <mytaglib:i18n
										key="map.show.distance" />
								</a>

							</div>

						</div>
					</form:form>
				</div>
				3
				<div class="row">
					<a class="waves-effect waves-light btn red"
						onclick="clearDistance()"> <mytaglib:i18n
							key="map.clear.distance" />
					</a>
				</div>
			</div>
			<div class="col s6">
				<div class="row">
					4
					<mytaglib:i18n key="map.show.route" />
				</div>
				<div class="row">
					5
					<form:form class="col s12" action="#" modelAttribute="idHolder"
						method="get">
						<div class="row">

							<div class="col s12">

								<form:select path="id" cssClass="route-selector">
									<option value="" disabled selected><mytaglib:i18n
											key="map.routes" />
										<form:options items="${mapRoutes}" />
								</form:select>
								<label for="id"><mytaglib:i18n key="map.routes" /></label>
							</div>

						</div>
						<div class="row">

							<div class="col s12">
								<a class="waves-effect waves-light btn"
									onclick="showSelectedTrack()"> <mytaglib:i18n
										key="map.show.route" />
								</a>

							</div>

						</div>
					</form:form>
				</div>
				6
				<div class="row">
					<a class="waves-effect waves-light btn red" onclick="clearRoutes()">
						<mytaglib:i18n key="map.clear.routes" />
					</a>
				</div>
			</div>
		</div>

	</div>
</div>

<div class="row">
	<div class="col s12">
		<div>
			<script>
			var mapId=${formModel.id};
		
			 ymaps.ready(initMapWithImage.bind(null,
					 mapId,
					${formModel.latitude1},
					${formModel.longitude1},
					${formModel.latitude2},
					${formModel.longitude2}));
						</script>
			<div id="map" style="width: 100%; height: 700px"></div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col s5"></div>
	<div class="col s2">
		<a class="waves-effect waves-light btn" href="${baseUrl}"><mytaglib:i18n
				key="map.back" /><i class="material-icons right">undo</i> </a>
	</div>
	<div class="col s5"></div>
</div>


<script type="text/javascript">
function showSelectedDistance(){
	var selectedRouteId=$( "select.dist-selector" ).val();
	var contextUrl = '${contextPath}';
	var pointStart=[];
	var pointFinish=[];
	var circles=[];
	var count=0;
$.get(contextUrl + "/route/points?routeId=" + selectedRouteId, function(
		routeData) {
	var points=[];
	
	
	var pointsData=routeData.points;
	var routeName=routeData.name;
	var length=pointsData.length;
	pointsData.forEach(function(p) {
		var lat = p.latitude;
		var lon = p.longitude;
		
		points.push([ lat, lon ]);
		
		
		var c = new ymaps.Circle([
	       
	[lat, lon],
	        40
	    ], {
	            hintContent : routeName,
	    }, {
	      

	        fillColor: "#DB709300",
	        strokeColor: "#990066",
	        strokeOpacity: 0.8,
	        strokeWidth: 4


	    });

		circles.push(c);
				
	});
	
	var line = new ymaps.GeoObject({
		geometry : {
			type : "LineString",
			coordinates : points
		},
		properties : {
			hintContent : routeName,
		
		}
	}, {
		draggable : false,
		strokeColor : "#990066",
		strokeWidth : 4
	});
	myCollectionDistance.add(line);
	
	
	
	pointStart= points[0];
	pointFinish= points[points.length - 1];
	
	for (var i = 1; i < circles.length-1; i++) {
		 myCollectionDistance.add(circles[i]);
	};
	
	
		

	
 var start = new ymaps.Circle([
	 pointStart,
		        40
		    ], {
		        hintContent: "start circle"
		    }, {
		        fillColor: "#00e67689",
		          strokeColor: "#990066",
		        strokeOpacity: 0.9,
		        strokeWidth: 4
		    });
 
 myCollectionDistance.add(start);
 
var finish = new ymaps.Circle([
	pointFinish,
		        40
		    ], {
		        hintContent: "finish circle"
		    }, {
		        fillColor: "#DB709300",
		          strokeColor: "#990066",
		        strokeOpacity: 0.8,
		        strokeWidth: 6
		    });
		    
 myCollectionDistance.add(finish);
 
 var finish1 = new ymaps.Circle([
		pointFinish,
			        30
			    ], {
			         hintContent: "finish circle"
			    }, {
			        fillColor: "#99006699",
			          strokeColor: "#990066",
			        strokeOpacity: 0.8,
			        strokeWidth: 6
			    });
	 myCollectionDistance.add(finish1);
			
})
}



function showSelectedTrack(){
	
	function getRandomColor() {
		  var letters = '0123456789ABCDEF';
		  var color = '#';
		  for (var i = 0; i < 6; i++) {
		    color += letters[Math.floor(Math.random() * 16)];
		  }
		  return color;
		}
	
	var selectedRouteId=$( "select.route-selector" ).val();
		var contextUrl = '${contextPath}';
		
	$.get(contextUrl + "/route/points?routeId=" + selectedRouteId, function(
			routeData) {

		var points = [];
		
		var pointsData=routeData.points;
		var routeName=routeData.name;
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
				hintContent : routeName
						}
		}, {
			draggable : false,
			strokeColor : getRandomColor(),
			strokeWidth : 4
		});
		
	myCollectionRoute.add(myGeoObject);
	   
	});
}
	 
</script>







