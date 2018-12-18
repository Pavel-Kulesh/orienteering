<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/event" />
<div class="row">
	<div class="col s3"></div>
	<div class="col s6">
		<h4 class="header">
			<mytaglib:i18n key="event.name" />
			: ${formModel.name}
		</h4>
	</div>
	<div class="col s3"></div>
</div>



<div class="row">

	<div class="col s12">
		<ul class="collapsible" style="border-radius: 5px; font-size: 16pt; color: black">
			<li>
				<div class="collapsible-header" style="background-color: #e3f2fd">
					<mytaglib:i18n key="event.general" />
				</div>
				<div class="collapsible-body">
					<p>
						<mytaglib:i18n key="event.date" />
						:
						<fmt:formatDate pattern="dd-MM-yyyy" value="${formModel.date}" />
						<br />
						<mytaglib:i18n key="event.type" />
						: ${formModel.type} <br />
						<mytaglib:i18n key="event.country" />
						: ${formModel.countryName}
					</p>
				</div>
			</li>

			<li>
				<div class="collapsible-header" style="background-color: #cfd8dc">
					<mytaglib:i18n key="event.info" />

				</div>
				<div class="collapsible-body">
					<textarea style="height: 50%; border-radius: 5px; font-size: 16pt; color: black" disabled="disabled">${formModel.info} </textarea>
				</div>
			</li>

			<li>
				<div class="collapsible-header" style="background-color: #ffff8d">
					<mytaglib:i18n key="event.map" />
				</div>
				<div class="collapsible-body">
					<script>
						ymaps.ready(initMap.bind(null, ${formModel.latitude},
								${formModel.longitude}));
					</script>
					coordinates: ${formModel.latitude}-${formModel.longitude}

					<div id="map" style="width: 100%; height: 400px"></div>


				</div>
			</li>
		</ul>

	</div>
</div>

<div class="row center">

	<a class="btn waves-effect waves-light" href="${contextPath}/list/event/${formModel.id}"><mytaglib:i18n key="event.list.participant" /></a>

	<c:if test="${ regPossibility}">
		<c:if test="${registerToEvent}">
			<a href="${baseUrl}/registrationCustomerToEvent/${formModel.id}" class="btn waves-effect waves-light grey darken-3" type="submit"> <mytaglib:i18n key="event.register" />
			</a>
		</c:if>
		<c:if test="${deleteFromEvent}">
			<a href="${baseUrl}/deleteCustomerFromEvent/${formModel.id}" class="btn waves-effect waves-light red" type="submit"> <mytaglib:i18n key="event.unregister" />
			</a>
		</c:if>
	</c:if>

	<a class="waves-effect waves-light btn red" href="${baseUrl}"><mytaglib:i18n key="event.back" /><i class="material-icons right">undo</i></a>


</div>
<c:if test="${formModel.statusVisible}">
	<div class="fixed-action-btn">
		<a class="btn-floating btn-large red" href="${baseUrl}/${formModel.id}/edit"> <i class="large material-icons">mode_edit</i>
		</a>
	</div>
</c:if>

<script>
$(document).ready(function(){
    $('.fixed-action-btn').floatingActionButton();
  });
</script>




