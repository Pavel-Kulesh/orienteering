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

		<div class="col s3">
			<sec:authorize access="!isAnonymous()">
				<form:form class="col s12"
					action="${baseUrl}/addRouteToMap/${formModel.id}"
					modelAttribute="idHolder" method="post">
					<div class="row">
						<div class="col s12">
							<mytaglib:i18n key="map.add.route" />
							<form:select path="id">
								<option value="" disabled selected><mytaglib:i18n
										key="map.routes" />
									<form:options items="${myRoutes}" />
							</form:select>
							<form:errors path="id" cssClass="red-text" />
							<%-- <label for="id"><mytaglib:i18n key="map.routes" /></label> --%>

						</div>
					</div>
					<div class="row">
						<div class="col s12">
							<button class="btn waves-effect waves-light" type="submit">
								<mytaglib:i18n key="map.add.route" />
							</button>
						</div>


					</div>
				</form:form>
			</sec:authorize>
		</div>
		<div class="col s3">
			<sec:authorize access="!isAnonymous()">
				<form:form class="col s12"
					action="${baseUrl}/deleteRouteFromMap/${formModel.id}"
					modelAttribute="idHolder" method="get">
					<div class="row">
						<div class="col s12">
							<mytaglib:i18n key="map.delete.route" />
							<form:select path="id">
								<option value="" disabled selected><mytaglib:i18n
										key="map.routes" />
									<form:options items="${myRoutesOnMap}" />
							</form:select>
							<form:errors path="id" cssClass="red-text" />
							<label for="id"><mytaglib:i18n key="map.routes" /></label>

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
			</sec:authorize>
		</div>
		<div class="col s3">
			show selected distance

			<form:form class="col s12" action="#" modelAttribute="idHolder"
				method="get">
				<div class="row">
					<div class="col s12">
						<mytaglib:i18n key="map.show.route" />
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


		<div class="col s3">
			show selected track
			<form:form class="col s12" action="#" modelAttribute="idHolder"
				method="get">
				<div class="row">
					<div class="col s12">
						<mytaglib:i18n key="map.show.route" />
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




	</div>
	<div class="col s12">

		<div class="col s3"></div>
		<div class="col s3"></div>

		<div class="col s3">

			<a class="waves-effect waves-light btn red" onclick="clearDistance()">
				<mytaglib:i18n key="map.clear.distance" />
			</a>
		</div>
		<div class="col s3">

			<div class="col s12">
				<a class="waves-effect waves-light btn red" onclick="clearRoutes()">
					<mytaglib:i18n key="map.clear.routes" />
				</a>

			</div>
		</div>
	</div>
</div>




<div class="row">
	<div class="col s12">
		<div>
			<script>
			var contextUrl="${contextPath}";	
			var mapId=${formModel.id};
			var pathImage="";
			$.get(contextUrl + "/map/image/" + mapId, function(
					pointsData) {
				pathImage="data"+pointsData;
			});
			
			 ymaps.ready(initMapWithImage.bind(null,
					 '${formModel.path}',
					${formModel.latitude1},
					${formModel.longitude1},
					${formModel.latitude2},
					${formModel.longitude2}));
						</script>
			<div id="map" style="width: 100%; height: 700px"></div>
		</div>
	</div>
</div>




<!-- <div class="row">
	<div class="col s12">
		<div>
			<script>
			
			var mapId = '${formModel.id}';
			var contextUrl = '${contextPath}';
			
			 ymaps.ready(initMapWithImage.bind(null,
					 contextUrl,
					 mapId,
					${formModel.latitude1},
					${formModel.longitude1},
					${formModel.latitude2},
					${formModel.longitude2}));
						</script>
			<div id="map1" style="width: 100%; height: 500px"></div>
		</div>
	</div>
</div> -->









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
	        balloonContent: "any",
	        hintContent: "put me"
	    }, {
	      

	        fillColor: "#DB709300",
	        strokeColor: "#990066",
	        strokeOpacity: 0.8,
	        strokeWidth: 4


	    });

		circles.push(c);
				
	});
	
	
	for (var i = 1; i < points.length; i++) {
		var p1=points[i-1];
		var p2=points[i];
		
		var line = new ymaps.GeoObject({
            geometry: {
                type: "LineString",
                coordinates: [p1,p2],
            },
            properties:{
                hintContent: "x",
                balloonContent: "t"
            }
        }, {
            strokeColor: "#00e676",
            strokeWidth: 5
        });

		myCollectionDistance.add(line);
		
		
	}
	
	pointStart= points[0];
	pointFinish= points[points.length - 1];
	
	for (var i = 1; i < circles.length-1; i++) {
		 myCollectionDistance.add(circles[i]);
	};
	
	
	
	
	
	
	
	/*   for (var i = 1; i < circles.length; i++) {
		var c1=circles[i-1];
		var c2=circles[i];
		debugger;
		c1.geometry.setMap(window.globalMapReference);
		c2.geometry.setMap(window.globalMapReference);
		var x1=c2.geometry.getCoordinates();
		var coordX=c1.geometry.getClosest(x1).position;
		var line = new ymaps.GeoObject({
	        geometry: {
	            type: "LineString",
	            coordinates: [
	                 c1.geometry.getClosest(c2.geometry.getCoordinates()).position,
	                 c2.geometry.getClosest(c1.geometry.getCoordinates()).position,
	            ]
	        },
	        properties:{
	            hintContent: "zzz‚",
	            balloonContent: "xxxŒ"
	        }
	    }, {
	        strokeColor: "#990066",
	        strokeWidth: 5
	    });

	myCollectionDistance.add(line);
		
	}; 
  */

	
 var start = new ymaps.Circle([
     
	 pointStart,
		        40
		    ], {
		        balloonContent: "start circle",
		        hintContent: "start circle"
		    }, {
		      

		        fillColor: "#DB709300",
		          strokeColor: "#00e676",
		        strokeOpacity: 0.8,
		        strokeWidth: 4


		    });
 myCollectionDistance.add(start);
 
 
 
var finish = new ymaps.Circle([
     
	pointFinish,
		        40
		    ], {
		        balloonContent: "finish circle",
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
			        balloonContent: "finish circle",
			        hintContent: "finish circle"
			    }, {
			      

			        fillColor: "#DB709300",
			          strokeColor: "#990066",
			        strokeOpacity: 0.8,
			        strokeWidth: 6


			    });
	 myCollectionDistance.add(finish1);
 
 
	
	
	
	/* circles.forEach(function(p) {
		
		 myCollectionDistance.add(p);
		
	}) */
	
//	globalMapReference.geoObjects.add(myCollectionDistance);  		
})



//circles.forEach(function(p) {
		
//		 myCollectionDistance.add(p);
		
//	})


//globalMapReference.geoObjects.add(myCollectionDistance);  
	
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
				hintContent : "can write for every point routeName and show on map (need change PointDTO)",
						}
		}, {
			draggable : false,
			strokeColor : getRandomColor(),
			strokeWidth : 4
		});
		
		
		
		
	//	debugger;
	myCollectionRoute.add(myGeoObject);
	   
	});
}
	 
</script>







