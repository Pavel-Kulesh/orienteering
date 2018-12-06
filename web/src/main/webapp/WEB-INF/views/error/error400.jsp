<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div>Error Page</div>

<div>
	<br />Request from ${pageContext.errorData.requestURI} is failed
</div>
<div>
	<br /> Servlet name: ${pageContext.errorData.servletName}
</div>
<div>
	<br /> Status code: ${pageContext.errorData.statusCode}
</div>
<div>
	<br /> Message: Bad request
</div>



