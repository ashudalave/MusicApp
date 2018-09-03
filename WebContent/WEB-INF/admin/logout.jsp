<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
    <!-- use context-relative URL supported by c:url  -->
    <c:url var="topURL" value="/welcome.html" />
	<h1>You are now logged out</h1>
	<p>(i.e., your session is terminated)</p>
	<p><a HREF="${topURL}">Back to Site Homepage</a></p>

</body>
</html>