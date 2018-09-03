<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="admin">

<h1>Report list</h1>

<p>This page contains a list of the reports that are 
    available from this site.</p>
    
<form action="<c:url value='/admin/displayReport'/>" method="post">
    <input type="hidden" name="reportName" value="userEmail">
    <input type="hidden" name="reportTitle" value="The User Email report">
    <input type="submit" value="User Email Report" class="left_margin">
</form>

<form action="<c:url value='/admin/parameters'/>" method="post">
    <input type="hidden" name="reportName" value="downloadDetail">
    <input type="hidden" name="reportTitle" value="The Downloads report">
    <input type="submit" value="Downloads report" class="left_margin">
</form>

<form action="<c:url var="adminWelcomeUrl" value="/adminWelcome.html" />" method="post">
    <input type=submit value="Go Back to Menu">
</form>
</section>

<A href="${adminWelcomeUrl}">Admin Home </a> 
<jsp:include page="/includes/footer.jsp" />

