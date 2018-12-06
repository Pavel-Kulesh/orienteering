<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/participant" />
<h4 class="header"><mytaglib:i18n key="participant.header.info" /></h4>


<div class="row">
	<div class="col s2"></div>
	<div class="col s8">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="participant.name" /> <span class="badge"><i class="material-icons">sentiment_satisfied</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.name}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="participant.surname" /> <span class="badge"><i class="material-icons">sentiment_satisfied</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.surname}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<mytaglib:i18n key="participant.city" /> <span class="badge"><i class="material-icons">star</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.cityName}</p>
				</div>
			</li>
		</ul>
	</div>
	<div class="col s2"></div>
</div>

<div class="row">
	<div class="col s3"></div>
	<div class="col s3">
		<a href="${contextPath}/list/user/${formModel.id}"
			class="btn waves-effect waves-light right" type="submit"
			title="list of registered events "> <mytaglib:i18n key="participant.eventList" /> <i
			class="large material-icons">public</i>
		</a>
	</div>

	<div class="col s3">
		<a class="waves-effect waves-light btn" href="${baseUrl}"><mytaglib:i18n key="participant.back" /><i class="material-icons right">undo</i>
		</a>
	</div>
	<div class="col s3"></div>
</div>
