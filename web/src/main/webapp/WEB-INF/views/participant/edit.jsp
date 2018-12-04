<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/participant" />
<h4 class="header">Edit customer</h4>

<div>
	<form:form class="col s12" method="GET"
		action="${baseUrl}/list/user/id" modelAttribute="formModel">

		<a href="${contextPath}/list/user/${formModel.id}"
			class="btn waves-effect waves-light right" type="submit"
			title="list of registered events "><i
			class="large material-icons">home</i></a>

	</form:form>

</div>


<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="cityId" type="hidden" />


		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">Name</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="surname" type="text" disabled="${readonly}" />
				<form:errors path="surname" cssClass="red-text" />
				<label for="surname">Surname</label>
			</div>
		</div>
<%-- 
		<div class="row">
			<div class="input-field col s12">
				<form:select path="type">
					<option value="" disabled selected>Role
						<form:options items="${roleChoices}" />
				</form:select>
				<form:errors path="role" cssClass="red-text" />
				<label for="role">role</label>
			</div>
		</div> --%>





		<div class="row">
			<div class="input-field col s12">
				<form:input path="phone" type="text" disabled="${readonly}" />
				<form:errors path="phone" cssClass="red-text" />
				<label for="surname">Phone</label>
			</div>
		</div>



		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">Save</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}">Go
					back to listƒ<i class="material-icons right"></i>
				</a>
			</div>
		</div>

	</form:form>
</div>
