<%-- Note: we can't usefully put the page directive with the desired character
    encoding (UTF-8) here in this file even though we want the same one in all 
    pages because the JSP spec says "Only the character encoding specified for 
    the requested page is used; the encodings of files included via the include 
    directive are not taken into consideration." (JSP.4.2 Response Char Encoding)
    JSP will generate a HTML <meta> tag for UTF-8 from this info. 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<title>Music Store Shop </title>
<link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
 <link rel="shortcut icon" href="<c:url value='/images/guiInst.ico'/>">
<body style="background-color:hsl(0, 100%, 75%);">
	<header>
	    <img src="<c:url value='/images/instruments.jpg'/>" alt="Instruments" >
		<h1  style="text-align:center;">MUSIC SHOP</h1>
	</header>