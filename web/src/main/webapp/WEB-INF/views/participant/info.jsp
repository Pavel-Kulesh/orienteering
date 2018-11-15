<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/participant" />
<h4 class="header">Personal info</h4>


<div class="row">
	<div class="col s2"></div>
	<div class="col s8">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					Name <span class="badge"><i class="material-icons">sentiment_satisfied</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.name}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Surname <span class="badge"><i class="material-icons">sentiment_satisfied</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.surname}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					City <span class="badge"><i class="material-icons">star</i></span>
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
			title="list of registered events "> Events list <i
			class="large material-icons">public</i>
		</a>
	</div>

	<div class="col s3">
		<a class="waves-effect waves-light btn" href="${baseUrl}">Go to
			participant list<i class="material-icons right">undo</i>
		</a>
	</div>
	<div class="col s3"></div>
</div>
