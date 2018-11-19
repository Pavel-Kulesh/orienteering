<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/registration" />

<h4 class="header">REG</h4>
<div class="row">
	<div class="col s3"></div>
	<form:form class="col s6" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<div class="row">

			<i class="material-icons">email</i>
			<div class="input-field col s12">
				<form:input path="email" type="text" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email">email</label>
			</div>
		</div>
		<div class="row">
			<i class="material-icons">do_not_disturb_on</i>
			<div class="input-field col s12">
				<form:input path="password" type="password" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password">pass</label>
			</div>
		</div>

		<div class="row">

			<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">name</label>
			</div>
		</div>
		<div class="row">
			<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="surname" type="text" />
				<form:errors path="surname" cssClass="red-text" />
				<label for="surname">surname</label>
			</div>
		</div>

		<%-- <div class="row">
			<i class="material-icons">location_city</i>
			<div class="input-field col s12">
				<form:select path="cityId">
					<option value="" disabled selected>Select city</option>
					<form:options items="${cityChoices}" />
				</form:select>
				<form:errors path="cityId" cssClass="red-text" />
				<label for="cityId">city</label>
			</div>
		</div> --%>

		<div class="row">
			<div class="input-field col s12">
				<i class="material-icons prefix">visibility</i>
				<form:select path="countryId">
				</form:select>
				<form:errors path="countryId" cssClass="red-text" />
				<label for="country">Country</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<i class="material-icons prefix">visibility</i>
				<form:select path="cityId">
				</form:select>
				<form:errors path="cityId" cssClass="red-text" />
				<label for="cityId">City</label>
			</div>
		</div>



		<form:input path="role" value="" type="hidden" />


		<div class="row">
			<i class="material-icons">assignment_ind</i>
			<div class="input-field col s12">

				<form:input path="phone" type="text" />
				<form:errors path="phone" cssClass="red-text" />
				<label for="phone">phone number</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">register</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}">ÉGo
					back<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
	<div class="col s3"></div>
</div>


<script
	src="${pageContext.request.contextPath}/resources/js/init-combos.js"></script>
<script>
	initComboboxes('${pageContext.request.contextPath}');
</script>
