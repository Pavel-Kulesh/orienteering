<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/route" />
<h4 class="header">ROUTE INFO</h4>

<div class="row">
	
	<div class="col s12">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					Name <span class="badge"><i class="material-icons">assignment</i></span>
				</div>
				<div class="collapsible-body">
					<p> "${formModel.name}"</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Route <span class="badge"><i class="material-icons">announcement</i></span>
				</div>
				<div class="collapsible-body">
					<p>need add simple map+ route</p>
				</div>
			</li>
		</ul>
	</div>
</div>




<div class="row">
	<div class="col s5"></div>

	<div class="col s3">
		<a class="waves-effect waves-light btn" href="${baseUrl}">Back<i class="material-icons right">undo</i>
		</a>
	</div>

	<div class="col s5"></div>
</div>


