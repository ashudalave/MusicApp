<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<H1>Welcome !!</H1>
<H2>Please select one of the options below:</H2>

	<c:url var="catalogURL" value="catalog.html" />
	<c:url var="cartUrl" value="cart.html" />
<UL>	
	<LI><A href="${catalogURL}">Browse Catalog </a> </LI>
	<LI><A HREF="${cartUrl}">View Cart</A> </LI>
	<LI><A HREF="welcome.html">Back to Site Homepage</A></LI>
</UL>

<jsp:include page="/includes/footer.jsp" />
