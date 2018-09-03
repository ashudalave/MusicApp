<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- begin middle column -->
<section id="admin">
<jsp:useBean id="admin" scope="session" class="cs636.music.domain.Invoice" />
<h1>Your invoice</h1>

<table>
  <tr>
    <td><b>Date</b></td>
    <td>${invoice.invoiceDate}</td>
    <td></td>
  </tr>
  <tr>
    <td><b>Ship To</b></td>
    <td>${invoice.user.addressHTMLFormat}</td>
    <td></td>
  </tr>
  <tr><td colspan="3"><hr></td></tr>
  <tr>
    <td><b>Qty</b></td>
    <td><b>Description</b></td>
    <td><b>Price</b></td>
  </tr>
  
  <c:forEach var="item" items="${invoice.lineItems}">
  <tr>
    <td>${item.quantity}</td>
    <td>${item.product.description}</td>
    <td>${item.totalCurrencyFormat}</td>
  </tr>
  </c:forEach>

  <tr><td colspan="3"><hr></td></tr>
  <tr>
    <td><b>Total</b></td>
    <td></td>
    <td><p>${invoice.invoiceTotalCurrencyFormat}</td>
  </tr>      
</table>

<label>Payment information</label>
<span>${invoice.user.creditCardType}: ${invoice.user.creditCardNumber} 
    (${invoice.user.creditCardExpirationDate})</span><br>
<label>Email Address</label>
<span>${invoice.user.email}</span><br>

<form action="processInvoice" method="post">
  <input type="submit" value="Process Invoice">
</form>
<form action="displayInvoices" method="post">
  <input type="submit" value="View Unprocessed Invoices">
</form>

</section>

<jsp:include page="/includes/footer.jsp" />