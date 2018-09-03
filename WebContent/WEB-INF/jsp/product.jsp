<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id=product> 
<H1>Product Details</H1>
<H2>${product.description}</H2>

<c:url var="listenURL" value="${listenNextUrl}" />
<c:url var="addToCartUrl" value="cart.html">
	<c:param name="addItem" value="true"/>
</c:url>

<ul>
	<li>Product Code: ${product.code}</li>
	<li>Price: ${product.price}</li>
	<li><a href="${listenURL}">Listen to samples</a></li>
	<li><a href="${addToCartUrl}">Add to Cart</a></li>
</ul>

	<c:url var="catalogUrl" value="catalog.html" />
	<c:url var="cartUrl" value="cart.html" />
	<c:url var="StudentWelcomeUrl" value="StudentWelcome.html" />
<UL>	
	<LI><A href="${catalogUrl}">Browse Catalog </a> </LI>
	<LI><A HREF="${cartUrl}">View Cart</A> </LI>
	<LI><A href="${StudentWelcomeUrl}">Student Home </a> </LI>
</UL>
</section>
<jsp:include page="/includes/footer.jsp" />