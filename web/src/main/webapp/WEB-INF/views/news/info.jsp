<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/news" />
<h4 class="header">News info: ${formModel.name}</h4>

<div class="row">
	<div class="col s3"></div>
	<div class="col s6">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					Name <span class="badge"><i class="material-icons">assignment</i></span>
				</div>
				<div class="collapsible-body">
					<p>News: "${formModel.name}"</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Info <span class="badge"><i class="material-icons">announcement</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.info}</p>
				</div>
			</li>
		</ul>
	</div>
	<div class="col s3"></div>
</div>

<div class="row">
	<div class="col s2"></div>
	<div class="col s8">
		<div class="card-panel teal">
			<span class="white-text">${formModel.info} </span>
		</div>
	</div>
	<div class="col s2"></div>
</div>


<div class="row">
	<div class="col s5"></div>

	<div class="col s3">
		<a class="waves-effect waves-light btn" href="${baseUrl}">Back to
			news<i class="material-icons right">undo</i>
		</a>
	</div>

	<div class="col s5"></div>
</div>


