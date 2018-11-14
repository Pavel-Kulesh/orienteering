<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">Event: ${formModel.name}</h4>


<div class="row">
	<div class="col s3"></div>
	<div class="col s6">
		<ul class="collapsible">
			<li>
				<div class="collapsible-header">
					Date <span class="badge"><i class="material-icons">query_builder</i></span>
				</div>
				<div class="collapsible-body">
					<p>
						<fmt:formatDate pattern="dd-MM-yyyy" value="${formModel.date}" />
					</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Type <span class="badge"><i class="material-icons">directions_run</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.type}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Country <span class="badge"><i class="material-icons">public</i></span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.countryName}</p>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					Info <span class="badge"><i class="material-icons">description</i>
					</span>
				</div>
				<div class="collapsible-body">
					<p>${formModel.info}</p>
				</div>
			</li>

			<li>
				<div class="collapsible-header">
					Map <span class="badge"><i class="material-icons">public</i>
					</span>
				</div>
				<div class="collapsible-body">
					<p>insert map here</p>
				</div>
			</li>
		</ul>

	</div>
	<div class="col s3"></div>
</div>



<div class="row">
	<div class="col s5"></div>

	<sec:authorize access="!isAnonymous()">
		<div class="col s2">
			<c:if test="${registerToEvent}">
				<a href="${baseUrl}/registrationCustomerToEvent/${formModel.id}"
					class="btn waves-effect waves-light" type="submit"> Register to	event <i class="large material-icons">rowing</i>
				</a>
			</c:if>
			<c:if test="${deleteFromEvent}">
				<a href="${baseUrl}/deleteCustomerFromEvent/${formModel.id}"
					class="btn waves-effect waves-light" type="submit"> Unregister from event<i class="large material-icons">rowing</i>
				</a>
			</c:if>
		</div>

	</sec:authorize>
	<div class="col s5"></div>
</div>




<div class="row">
	<div class="col s3"></div>
	<div class="col s3">
		<a href="${contextPath}/list/event/${formModel.id}"
			class="btn waves-effect waves-light" type="submit"> LIST OF
			PARTICIPANTS <i class="large material-icons">public</i>
		</a>
	</div>

	<div class="col s3">
		<a class="waves-effect waves-light btn" href="${baseUrl}">Go to
			events list<i class="material-icons right">undo</i>
		</a>
	</div>
	<div class="col s3"></div>
</div>
