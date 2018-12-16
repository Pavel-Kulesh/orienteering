<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}/route" />
<h4 class="header">Edit Route</h4>


<div class="row">
	<div class="col s2"></div>
	<form:form class="col s8" method="POST" action="${baseUrl}/edit"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="customerId" type="hidden" />

		<div class="row">
			<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">Name</label>
			</div>
		</div>

		<sec:authorize access="hasRole('ADMIN')">
			<div class="row">
				<i class="material-icons">filter</i>
				<form:select path="track">
					<option value="" disabled selected><mytaglib:i18n
							key="route.way" /></option>
					<form:options items="${wayChoices}" />
				</form:select>
				<label for="track"><mytaglib:i18n key="route.way" /></label>
			</div>
		</sec:authorize>



		<div class="row">
			<div class="col s2"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">Save</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right red" href="${baseUrl}"
					title="Go back to maps list">Go back to route listƒ<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
	<div class="col s4"></div>
</div>
