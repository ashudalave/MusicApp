<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 

<H4>Database has been initialized.</H4>



<c:url var="adminWelcomeUrl" value="adminWelcome.html" />
<A href="${adminWelcomeUrl}">Admin Home </a> 

<jsp:include page="/includes/footer.jsp" />