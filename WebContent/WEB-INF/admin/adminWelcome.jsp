<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<H1>Welcome to the Admin Page!</H1>
<p> <font color="blue">admin login needs to be handeled</font></p>

	<c:url var="initDbUrl" value="initdb.html" />
	<c:url var="processInvoicesUrl" value="processInvoices.html" />
	<c:url var="reportUrl" value="displayReports.html" />
<UL>	
	<LI><A href="${initDbUrl}">Initialize database </a> </LI>
	<LI><A HREF="${processInvoicesUrl}">Process invoices</A> </LI>
	<LI><A HREF="${reportUrl}">View reports</A> </LI>
	<LI><A HREF="/music3/welcome.html">Back to Site Homepage</A></LI>
</UL>

</body>
</html>
