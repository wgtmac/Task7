<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="template-customer-top.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h2 class="page-header">Transaction History</h2>
	</div>
</div>
<div class="table-responsive">
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Transaction Date</th>
				<th>Status</th>
				<th>Operation</th>
				<th>Fund Name</th>
				<th>Shares</th>
				<th>Share Price</th>
				<th>Total Amount</th>
			</tr>
		</thead>
		<!-- Create for each loop to fill table -->
		<tbody>
			<c:choose>
				<c:when test="${ (empty transactionList) }"></c:when>
				<c:otherwise>
					<c:forEach var="u" items="${ transactionList }">
						<tr>
							<td>${ u.getDate() }</td>
							<td>${ u.getStatus() }</td>
							<td>${ u.getOperation() }</td>
							<td>${ u.getFund() }</td>
							<td align="right">${ u.getShares() }</td>
							<td align="right"><fmt:setLocale value="en_US" /> <fmt:formatNumber
								value="${ u.getSharePrice() }" type="CURRENCY"> </fmt:formatNumber></td>
							<td align="right"><fmt:setLocale value="en_US" /> <fmt:formatNumber
								value="${ u.getAmount() }" type="CURRENCY"> </fmt:formatNumber></td>
							</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<p align="center">*** End of Transactions ***</p>
</div>

<jsp:include page="template-buttom.jsp" />