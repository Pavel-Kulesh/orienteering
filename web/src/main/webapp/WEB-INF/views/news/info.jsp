<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/news" />
<h4 class="header">
	${formModel.name}
</h4>


<div class="row">
	<div class="col s12">

		<textarea style="height: 70%; border-radius: 5px; font-size: 16pt; color: black " disabled="disabled">${formModel.info} </textarea>

	</div>
</div>


<div class="row">
	<div class="col s5"></div>

	<div class="col s3">
		<a class="waves-effect waves-light btn  blue-grey darken-3"
			href="${baseUrl}"><mytaglib:i18n key="news.back" /><i
			class="material-icons right ">undo</i> </a>
	</div>

	<div class="col s5"></div>
</div>


