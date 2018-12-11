<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/news" />
<h4 class="header">
	<mytaglib:i18n key="news.add" />
</h4>
<div class="row">

	<div class="col s3"></div>
	<form:form class="col s6" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />


		<div class="row">

			<div class="input-field col s9">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="news.name" /></label>
			</div>
			<div class="col s3">
				<i class="material-icons" title="Name info">assignment</i>
			</div>
		</div>

		<div class="row">

			<div class="input-field col s9">
				<form:input path="info" type="text" />
				<form:errors path="info" cssClass="red-text" />
				<label for="info"><mytaglib:i18n key="news.info" /></label>
			</div>
			<div class="col s3">
				<i class="material-icons" title="News info">announcement</i>
			</div>
		</div>

		<div class="row">
			<div class="col s2"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="save"/>ƒ</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right red" href="${baseUrl}"><mytaglib:i18n key="news.back" />ƒ<i class="material-icons right"></i>
				</a>
			</div>
			<div class="col s4"></div>
		</div>
	</form:form>
	<div class="col s3"></div>
</div>
