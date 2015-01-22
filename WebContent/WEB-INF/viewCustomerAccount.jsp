<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h2 class="page-header">${customerName}'s Account</h2>
	</div>
	<form action="reset.do">
		<input type="hidden" name="customer" value="${userName}">
		&nbsp; <input type="submit" value="Reset Customer Password"
			name="username">
	</form>
</div>
<p>
<div class="col-lg-6">
	<h2>Customer information:</h2>
	<div class="table-responsive">
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<td>Account User Name:</td>
					<td>${userName}</td>
				</tr>
				<tr>
					<td>Customer Name:</td>
					<td>${customerName}</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Address:</td>
					<td>${address1 }<br>${address2 }</td>
				</tr>
				<tr>
					<td>City, State:</td>
					<td>${city }, ${state}.</td>
				</tr>
				<tr>
					<td>Last trading date:</td>
					<td>${lastDay}</td>
				</tr>
				<tr>
					<td>Last Posted Balance:</td>
					<td align="right"><b>$ ${cash}</b></td>
				</tr>
				<tr>
					<td>Available Cash:</td>
					<td align="right"><b>$ ${avai_cash}</b></td>
				</tr>
			</tbody>
		</table>
	</div>
	<h2>Funds owned:</h2>
	<div class="table-responsive">
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>Fund Name</th>
					<th align="center">Shares</th>
					<th align="center">Value</th>
				</tr>
			</thead>
			<tbody>

				<c:choose>
				
					<c:when test="${(empty positionInfoList)}"></c:when>
					<c:otherwise>
						<c:forEach var="u" items="${positionInfoList}">
							<tr>
								<td >${ u.getName() }</td>
								<td align="right">${ u.getShares() }</td>
								<td align="right">${u.getTotal()}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>

<jsp:include page="template-buttom.jsp" />