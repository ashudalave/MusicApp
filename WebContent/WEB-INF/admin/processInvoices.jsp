<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<H1>Process Invoices</H1>
<H2>Choose an invoice to process.</H2>

<ul>
	<c:forEach items="${invoices}" var="invoice">
		<c:url value="processInvoices.html" var="processInvoicesUrl">
			<c:param name="invoiceIdToProcess" value="${invoice.invoiceId}"/>
		</c:url>
		<li>${invoice.invoiceId} - ${invoice.invoiceDate} - ${invoice.totalAmount} - ${invoice.user.emailAddress} - <a href="${processInvoicesUrl}">Process</a></li>
	</c:forEach>
</ul>

	<c:url var="adminWelcomeUrl" value="adminWelcome.html" />

<A href="${adminWelcomeUrl}">Admin Home </a> 
<jsp:include page="/includes/footer.jsp" />
