<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/registration" />

<h4 class="header">
	<mytaglib:i18n key="reg" />
</h4>
<div class="row">
	<div class="col s3"></div>
	<form:form class="col s6" method="POST" action="${baseUrl}" modelAttribute="formModel">
		<div class="row">

			<i class="material-icons">email</i>
			<div class="input-field col s12">
				<form:input path="email" type="text" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email"><mytaglib:i18n key="reg.email" /></label>
			</div>
		</div>



		<div class="row">
			<i class="material-icons">do_not_disturb_on</i>
			<div class="input-field col s12">
				<form:input path="password" type="password" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password"><mytaglib:i18n key="reg.pass" /></label>
			</div>
		</div>

		<div class="row">

			<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="participant.name" /></label>
			</div>
		</div>
		<div class="row">
			<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="surname" type="text" />
				<form:errors path="surname" cssClass="red-text" />
				<label for="surname"><mytaglib:i18n key="participant.surname" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<i class="material-icons prefix">location_city</i>
				<form:select path="countryId">
				</form:select>
				<form:errors path="countryId" cssClass="red-text" />
				<label for="country"><mytaglib:i18n key="country" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<i class="material-icons prefix">location_city</i>
				<form:select path="cityId">
				</form:select>
				<form:errors path="cityId" cssClass="red-text" />
				<label for="cityId"><mytaglib:i18n key="city" /></label>
			</div>
		</div>



		<form:input path="role" value="" type="hidden" />


		<div class="row">
			<i class="material-icons">phone</i>
			<div class="input-field col s12">

				<form:input path="phone" type="text" />
				<form:errors path="phone" cssClass="red-text" />
				<label for="phone"><mytaglib:i18n key="participant.phone" /></label>
			</div>
		</div>

		<div class="row center">
			
			<div class="col s6">
				<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="registration" /></button>
			</div>
			<div class="col s6">
				<a class="btn waves-effect waves-light right red" href="${url}"><mytaglib:i18n key="back" /> </a>
			</div>
		</div>
	</form:form>
	
</div>


<script
	src="${pageContext.request.contextPath}/resources/js/init-combos.js"></script>
<script>
	initComboboxes('${pageContext.request.contextPath}');
</script>
