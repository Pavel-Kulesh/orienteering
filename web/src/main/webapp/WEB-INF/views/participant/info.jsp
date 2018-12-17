<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/participant" />

<div style="font-size: 22px" class="row">
	<div class="col s2"></div>
	<div class="col s8">
		<h4 class="header">
			<mytaglib:i18n key="participant.header.info" />
		</h4>
		<br />
		<div class="row">
			<mytaglib:i18n key="participant.name" />
			: ${formModel.name}
		</div>
		<div class="row">
			<mytaglib:i18n key="participant.surname" />
			: ${formModel.surname}
		</div>
		<div class="row">
			<mytaglib:i18n key="participant.city" />
			: ${formModel.cityName}
		</div>
	</div>
	<div class="col s2"></div>
</div>

<div class="row">
	<div class="col s3"></div>
	<div class="col s3">
		
		<a href="${contextPath}/list/user/${formModel.id}"
			class="btn waves-effect waves-light" title="list of registered events "> <mytaglib:i18n
				key="participant.eventList" /></a>
	</div>

	<div class="col s3">
		<a class="btn waves-effect waves-light right red" href="${baseUrl}"><mytaglib:i18n
				key="participant.back" /><i class="material-icons right">undo</i> </a>
	</div>
	<div class="col s3"></div>
</div>
