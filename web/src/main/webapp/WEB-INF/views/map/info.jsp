<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">Map info: ${formModel.name}</h4>

<div class="row">
	<div class="col s12">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					Name <span class="badge"><i class="material-icons">sentiment_satisfied</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.name}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Info <span class="badge"><i class="material-icons">star</i></span>
				</div>
				<div class="collapsible-body">
					<p>Event's map</p>

				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Map <span class="badge"><i class="material-icons">public</i></span>
				</div>
				<div class="collapsible-body">
					<p>
						<script>
						var contextUrl = '${contextPath}';
						var mapId = '${formModel.id}';
							ymaps.ready(initMapWithImage.bind(null,
									'${formModel.path}',
									${formModel.latitude1},
									${formModel.longitude1},
									${formModel.latitude2},
									${formModel.longitude2}));
						</script>
					<div id="map1" style="width: 100%; height: 500px"></div>
				</div>
			</li>

			<li>
				<div class="collapsible-header">
					Info <span class="badge"><i class="material-icons">star</i></span>
				</div>
				<div class="collapsible-body">
					<ul>
						<li><a href="${baseUrl}/image-manual-response">Image
								manually add to response</a></li>
						<li><a href="${baseUrl}/image-byte-array.jpg">Image
								Download <i>byte[]</i> Example
						</a></li>
						<li><a href="${baseUrl}/image-response-entity.jpg">Image
								Download <i>ResponseEntity</i> Example
						</a></li>
					</ul>

				</div>
			</li>

		</ul>
	</div>
</div>



<div class="row">
	<div class="col s4">

		<form:form class="col s12"
			action="${baseUrl}/addRouteToMap/${formModel.id}"
			modelAttribute="formModel" method="get" enctype="multipart/form-data">
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					Select route to add on map list
					<form:select path="routeId">
						<option value="" disabled selected>Routes
							<form:options items="${routeChoices}" />
					</form:select>
					<label for="routeId">Route</label>

				</div>
				<div class="col s2"></div>
			</div>
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					<input type="submit" value="Add route to map" />
				</div>
				<div class="col s2"></div>


			</div>
		</form:form>


	</div>
	<div class="col s4">

		<form:form class="col s12"
			action="${baseUrl}/deleteRouteFromMap/${formModel.id}"
			modelAttribute="formModel" method="get" enctype="multipart/form-data">
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					Select my route to delete from map list
					<form:select path="routeId">
						<option value="" disabled selected>Routes
							<form:options items="${customerRoutes}" />
					</form:select>
					<label for="routeId">Route</label>

				</div>
				<div class="col s2"></div>
			</div>
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					<input type="submit" value="Delete route from map" />
				</div>
				<div class="col s2"></div>


			</div>
		</form:form>

	</div>
	<div class="col s4">

		<form:form class="col s12" action="#" modelAttribute="formModel"
			method="get" enctype="multipart/form-data">
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					Select route to display on map
					<form:select path="routeId">
						<option value="" disabled selected>Routes
							<form:options items="${mapRoutes}" />
					</form:select>
					<label for="routeId">Route</label>
				</div>
				<div class="col s2"></div>
			</div>
			<div class="row">
				<div class="col s2"></div>
				<div class="col s8">
					<button type="button" onclick="showSelectedTrack()">Show
						no map</button>
				</div>
				<div class="col s2"></div>


			</div>
		</form:form>









	</div>
</div>



<div class="row">
	<div class="col s5"></div>

	<div class="col s2">
		<a class="waves-effect waves-light btn" href="${baseUrl}">Go to
			maps list<i class="material-icons right">undo</i>
		</a>
	</div>
	<div class="col s5"></div>
</div>



<script type="text/javascript">


function showSelectedTrack(){
	var selectElment=$( "select#routeId" );
	alert ('show selected track'+selectElment);
}
</script>





