<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/news" />
<h4 class="header">Edit News</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		
		<div class="row">
		<i class="material-icons">assignment</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">Name</label>
			</div>
		</div>

		<div class="row">
		<i class="material-icons">announcement</i>
			<div class="input-field col s12">
				<form:input path="info" type="text" disabled="${readonly}" />
				<form:errors path="info" cssClass="red-text" />
				<label for="info">Info</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">Save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}">Go
					back to list�<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
