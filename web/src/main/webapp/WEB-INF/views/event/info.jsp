<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
	<div class="col s3"></div>
	<div class="col s6">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="event.date" />
					<span class="badge"><i class="material-icons">query_builder</i></span>
				</div>
				<div class="collapsible-body">
					<p>
						<fmt:formatDate pattern="dd-MM-yyyy" value="${formModel.date}" />
					</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="event.type" />
					<span class="badge"><i class="material-icons">directions_run</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.type}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="event.country" />
					<span class="badge"><i class="material-icons">public</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.countryName}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="event.info" />
					<span class="badge"><i class="material-icons">description</i>
					</span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.info}</p>
				</div>
			</li>

			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="event.map" />
					<span class="badge "><i class="material-icons">public</i> </span>
				</div>
				<div class="collapsible-body">

					<script>
						ymaps.ready(initMap.bind(null, ${formModel.latitude},
								${formModel.longitude}));
					</script>
					<div id="map" style="width: 100%; height: 300px"></div>


				</div>
			</li>
		</ul>

	</div>
	<div class="col s3"></div>
</div>

<div class="row">
	<div class="col s5"></div>

	<sec:authorize access="!isAnonymous()">
		<div class="col s2">
			<c:if test="${registerToEvent}">
				<a href="${baseUrl}/registrationCustomerToEvent/${formModel.id}"
					class="btn waves-effect waves-light grey darken-3" type="submit">
					<mytaglib:i18n key="event.register" /> <i
					class="large material-icons">rowing</i>
				</a>
			</c:if>
			<c:if test="${deleteFromEvent}">
				<a href="${baseUrl}/deleteCustomerFromEvent/${formModel.id}"
					class="btn waves-effect waves-light red" type="submit"> <mytaglib:i18n
						key="event.unregister" /><i class="large material-icons">rowing</i>
				</a>
			</c:if>
		</div>

	</sec:authorize>
	<div class="col s5"></div>
</div>




<div class="row">
	<div class="col s3"></div>
	<div class="col s3">
		<a href="${contextPath}/list/event/${formModel.id}"
			class="btn waves-effect waves-light" type="submit"> <mytaglib:i18n
				key="event.list.participant" /> <i class="large material-icons">public</i>
		</a>
	</div>

	<div class="col s3">
		<a class="waves-effect waves-light btn" href="${baseUrl}"><mytaglib:i18n
				key="event.back" /><i class="material-icons right">undo</i> </a>
	</div>
	<div class="col s3"></div>
</div>
