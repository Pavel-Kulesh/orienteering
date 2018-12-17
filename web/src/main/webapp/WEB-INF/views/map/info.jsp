<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">
	<mytaglib:i18n key="map.info" />
	: ${formModel.name}
</h4>

<c:if test="${not empty routes}">
	<input type="text" id="myInput" onkeyup="myFunction()" placeholder="<mytaglib:i18n key="search" />">

	<table id="myTable" class="bordered highlight">
		<thead>
			<tr>
				<th><mytaglib:i18n key="map.name" /></th>
				<th><mytaglib:i18n key="map.type" /></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="route" items="${routes}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${route.name}" /></td>
					<td><c:out value="${route.track}" /></td>
					<td class="right"><a class="waves-effect waves-light btn-floating" onclick="handleRouteClick(${route.id},'${route.track}')" title="<mytaglib:i18n key="map.show" />"> <i
							class="material-icons">brush</i>
					</a> <c:if test="${route.canEdit}">
							<a class="btn-floating red" href="${baseUrl}/deleteWayFromMap/${formModel.id}/route/${route.id}"><i class="material-icons">delete</i></a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />

	<div class="row">
		<div class="col s5"></div>
		<div class="col s1">
			<div class="right">
				<a class="btn red" onclick="clearDistance()" title="<mytaglib:i18n key="map.clear.distance" />"><i class="material-icons">content_cut</i> </a>

			</div>
		</div>
		<div class="col s1"></div>
		<div class="col s1">
			<a class="btn-floating red" onclick="clearWay()" title="<mytaglib:i18n key="map.clear.way" />"> <i class="material-icons">clear</i>
			</a>
		</div>
		<div class="col s4"></div>
	</div>

</c:if>
<br />
<div class="row">
	<div class="col s3">
		<sec:authorize access="!isAnonymous()">
			<div class="card">
				<div class="card-content center-align" style="padding: 10px">
					<div class="row">
						<span class="card-title" style="font-size: 14pt"><mytaglib:i18n key="map.add.route" /></span>
						<form:form class="col s12" action="${baseUrl}/addWayToMap/${formModel.id}" modelAttribute="idHolder" method="post">
							<form:select path="id">
								<option value="" disabled selected><mytaglib:i18n key="map.routes" />
									<form:options items="${ways}" />
									<form:errors path="id" cssClass="red-text" />
							</form:select>
							<button class="btn waves-effect waves-light" type="submit">
								<mytaglib:i18n key="map.add.route" />
							</button>
						</form:form>
						<sec:authorize access="hasRole('ADMIN')">
							<form:form class="col s12" action="${baseUrl}/addWayToMap/${formModel.id}" modelAttribute="idHolder" method="post">
								<form:select path="id">
									<option value="" disabled selected><mytaglib:i18n key="map.distances" />
										<form:options items="${distances}" />
								</form:select>
								<button class="btn waves-effect waves-light" type="submit">
									<mytaglib:i18n key="map.add.distance" />
								</button>
							</form:form>
						</sec:authorize>
					</div>
				</div>
			</div>
		</sec:authorize>
	</div>
	<div class="col s9">
		<div class="card">
			<div id="map" style="width: 100%; height: 500px"></div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col s5"></div>
	<div class="col s3 center">
		<a class="waves-effect waves-light btn" href="${baseUrl}"><mytaglib:i18n key="map.back" /><i class="material-icons right">undo</i> </a>
	</div>
	<div class="col s4"></div>
</div>
<script type="text/javascript">

var mapId=${formModel.id};
ymaps.ready(initMapWithImage.bind(null,
        mapId,
       ${formModel.latitude1},
       ${formModel.longitude1},
       ${formModel.latitude2},
       ${formModel.longitude2}));

function myFunction() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");

	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function handleClearClick(type){
	if (type==='WAY') return clearWay();
	if (type==='DISTANCE') return clearDistance();
	}

function handleRouteClick(routeId, type){
	if (type==='WAY') return showSelectedTrack(routeId);
	if (type==='DISTANCE') return showSelectedDistance(routeId);
}

function showSelectedDistance(routeId){
	clearDistance();
	var selectedRouteId=routeId;
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

function showSelectedTrack(routeId){
	
	function getRandomColor() {
		  var letters = '0123456789ABCDEF';
		  var color = '#';
		  for (var i = 0; i < 6; i++) {
		    color += letters[Math.floor(Math.random() * 16)];
		  }
		  return color;
		}
	
	var selectedRouteId=routeId;
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
