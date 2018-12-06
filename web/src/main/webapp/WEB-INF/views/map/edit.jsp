<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">
	<mytaglib:i18n key="map.edit" />
</h4>


<div class="row">
	<div class="col s2"></div>
	<form:form class="col s8" method="POST" action="${baseUrl}/edit"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="customerId" type="hidden" />
		<form:input path="file" type="hidden" />
		<form:input path="path" type="hidden" />
		
		
		
		
		<form:input path="routeId" type="hidden" value="1" />





		<div class="row">
			<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="map.name" /></label>
			</div>
		</div>

		<table>
			<tr>
				<td><div class="row">
						<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="latitude1" type="text" />
							<form:errors path="latitude1" cssClass="red-text" />
							<label for="latitude1"><mytaglib:i18n key="map.lt1" /></label>
						</div>
					</div></td>
				<td>
					<div class="row">
						<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="longitude1" type="text" />
							<form:errors path="longitude1" cssClass="red-text" />
							<label for="longitude1"><mytaglib:i18n key="map.lg1" /></label>
						</div>
					</div>
				</td>
			</tr>


			<tr>
				<td>


					<div class="row">
						<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="latitude2" type="text" />
							<form:errors path="latitude2" cssClass="red-text" />
							<label for="latitude2"><mytaglib:i18n key="map.lt2" /></label>
						</div>
					</div>


				</td>
				<td>
					<div class="row">
						<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="longitude2" type="text" />
							<form:errors path="longitude2" cssClass="red-text" />
							<label for="longitude2"><mytaglib:i18n key="map.lg2" /></label>
						</div>
					</div>
				</td>
			</tr>


		</table>





		<div class="row">
			<div class="col s2"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">
					<mytaglib:i18n key="save" />
				</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}"
					title="Go back to maps list"><mytaglib:i18n key="back" /><i
					class="material-icons right"></i> </a>
			</div>
		</div>
	</form:form>
	<div class="col s4"></div>
</div>
