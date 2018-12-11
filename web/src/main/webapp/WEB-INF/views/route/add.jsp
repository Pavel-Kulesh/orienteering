<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/route" />
<h4 class="header">Route</h4>
<div class="row">

	<div class="col s3"></div>
	<form:form class="col s6" method="POST" enctype="multipart/form-data"
		action="${baseUrl}" modelAttribute="formModel">

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

		<table>
			<tr>
				<td>File to upload:</td>
				<td><input type="file" name="fileDoc" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
			


		<div class="row">
			<div class="col s2"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">Saveƒ</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right red" href="${baseUrl}">Back<i
					class="material-icons right"></i>
				</a>
			</div>
			<div class="col s4"></div>
		</div>
	</form:form>
	<div class="col s3"></div>
</div>
