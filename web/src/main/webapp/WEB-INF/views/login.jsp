<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<br />
<br />
<br />
<div class="row">
	<div class="col s3"></div>
	<div class="col s6">
		<div class="card">
			<div class="card-content center-align" style="padding: 10px">
				<h2>
					<mytaglib:i18n key="login.info"></mytaglib:i18n>
				</h2>
				<form name='loginForm' action="<c:url value='login' />"
					method='POST'>
					<div class="row">
						<div class="col s3"></div>
						<div class="input-field col s6 center">
							<input type='text' name='username' value=''> <label
								for="username"><mytaglib:i18n key="login.email"></mytaglib:i18n>:</label>

						</div>
						<div class="col s3"></div>
					</div>
					<div class="row">
						<div class="col s3"></div>
						<div class="input-field col s6 center">
							<input type='password' name='password' /><label for="password"><mytaglib:i18n
									key="login.password"></mytaglib:i18n>:</label>
						</div>
						<div class="col s3"></div>
					</div>
					<c:if test="${not empty error}">
						<div class="row">
							<div class="col s12 center">
								<div class="error">
									<font color="#ff0000">${error}</font>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="row">
							<div class="col s12 center">
								<div class="msg">${msg}</div>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col s12 center">
							<button class="btn waves-effect waves-light " type="submit">
								<mytaglib:i18n key="login.submit"></mytaglib:i18n>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="col s3"></div>
</div>