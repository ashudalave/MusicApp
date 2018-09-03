<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/includes/header.jsp" />
<%-- <jsp:include page="/includes/column_left_admin.jsp" /> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<section id="admin">
    <p> Hi  <%= request.getRemoteUser()%> </p>
<h1>Admin Menu</h1>

<!-- these Form tags don't force a secure connection -->
<form action="<c:url value='/admin/displayInvoices'/>" method="post">
   <input type="submit" value="Process Invoices" class="left_margin">
</form>
<form action="<c:url value='/admin/displayReports'/>" method="post">
    <input type="submit" value="Display Reports" class="left_margin">
</form>

</section>
</head>
<body>

</body>
</html>
<jsp:include page="/includes/footer.jsp" />



