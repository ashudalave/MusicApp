<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id=register>
<H1>Register</H1>
<h2>Create a user account or sign in</h2>

		<!-- nextPageURL: listen.html or invoice.html-->
		<c:url var="nextPageUrl" value="${nextPageURL}"/>
		<form method="get" action="${nextPageUrl}">
			First name: <input type="text" name="firstName"> <br />
			Last name: <input type="text" name="lastName"> <br />
			Email Address: <input type="text" name="email"> <br />
			<input type="submit" value="Register">
		</form>


	<c:url var="catalogURL" value="catalog.html" />
	<c:url var="userWelcomeUrl" value="userWelcome.html" />
	<c:url var="cartURL" value="cart.html" />
<UL>	
	<LI><A HREF="${cartURL}">View Cart</A> </LI>
	<LI><A href="${catalogURL}">Browse Catalog </a> </LI>
	<LI><A href="${userWelcomeUrl}">User Home </a> </LI>
</UL>
</section>
<jsp:include page="/includes/footer.jsp" />
