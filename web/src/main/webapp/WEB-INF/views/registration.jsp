<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/registration" />

<h4 class="header">REG</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
			<div class="row">
			<div class="input-field col s12">
				<form:input path="email" type="text" disabled="${readonly}" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email">ZZZZZZ</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="password" type="text" disabled="${readonly}" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password">pass</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">name</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="surname" type="text" disabled="${readonly}" />
				<form:errors path="surname" cssClass="red-text" />
				<label for="surname">pass</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="cityId" type="text" disabled="${readonly}" />
				<form:errors path="cityId" cssClass="red-text" />
				<label for="cityId">city Id- need TEXT</label>
			</div>
		</div>
		<form:input path="role" value="" type="hidden" />
	<i class="tiny material-icons">assignment_ind</i>
		
		<div class="row">
			<div class="input-field col s12">
			<i class="material-icons">assignment_ind</i>
				<form:input path="phone" type="text" disabled="${readonly}" />
				<form:errors path="phone" cssClass="red-text" />
				<label for="phone">phone number</label>
			</div>
		</div>
		
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">ZZ</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}">ÉXX<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
