<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<title>Error Page</title>
<body>
	Request from ${pageContext.errorData.requestURI} is failed
	<br /> Servlet name: ${pageContext.errorData.servletName}
	<br /> Status code: ${pageContext.errorData.statusCode}
	<br /> Message: invalid value.
</body>
</html>
