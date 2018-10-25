<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/registration" />

<h4 class="header">REG</h4>
			<div id="register">
					<form:form name='registerForm' action="${contextPath}/register" method='POST' modelAttribute="formModel">
						<div class="row">

							<form:input path="role" value="${formModel.role}" type="hidden" />
							<form:input path="firstName" value="" type="hidden" />
							<form:input path="lastName" value="" type="hidden" />

							<div class="input-field col s12 center">
								<i class="material-icons prefix">mail_outline</i>

								<form:input id="regemail" path="email" type="email" class="validate" disabled="${readonly}" />
								<form:errors path="email" cssClass="red-text" />

								<label for="regemail" data-error="Wrong Email" data-success="right">Email</label>
							</div>

							<div class="input-field col s12">
								<i class="material-icons prefix">lock_outline</i>

								<form:input id="regpass" path="password" type="password" class="validate" disabled="${readonly}" />
								<form:errors path="email" cssClass="red-text" />

								<label for="regpass">Password</label>
							</div>

						</div>

						<div class="row">

							<div class="col s12 center-align">
								<button class="btn waves-effect waves-light cyan accent-4 hoverable" type="submit">
									REGISTER<i class="material-icons left" style="font-size: 250%">person</i>
								</button>
								<a class="btn waves-effect waves-light cyan accent-4 hoverable" href="${baseUrl}/ta">Home<i class="material-icons left">arrow_back</i></a>
							</div>

						</div>

					</form:form>
				</div>