<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">Map info: ${formModel.name}</h4>

<div class="row">
	<div class="col s2"></div>
	<div class="col s8">
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
							ymaps.ready(initMapWithImage.bind(null,
									'${formModel.path}',
									${formModel.latitude1},
									${formModel.longitude1},
									${formModel.latitude2},
									${formModel.longitude2}));
						</script>
					<div id="map1" style="width: 100%; height: 300px"></div>


				</div>
			</li>
		</ul>
	</div>
	<div class="col s2"></div>
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









