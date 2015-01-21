<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">${customer.getFirstName()}  ${customer.getLastName()}</h1>
		<h2>${customer.getAddress1()} ${customer.getAddress2()} ${customer.getCity()} ${customer.getState()} ${customer.getZipcode()}</h2>
	</div>
</div>

<div class="form-group">
	<label>Last Transaction Day</label> <input class="form-control" 	name="date" value="${lastTradingDay}" disabled>
	<p class="help-block">The above date is the date when you last
		performed any transaction</p>
</div>

<div class="form-group">
	<label>Cash Balance</label> <input class="form-control" name="cash"   value="${cash}"	disabled>
	<p class="help-block">The total amount in your account</p>
</div>

<div class="form-group">
	<label>Available Balance</label> <input class="form-control" name="balance"   value="${balance}"	disabled>
	<p class="help-block">The current amount available in your account</p>
</div>

<div class="form-group">
	<label>Your Funds</label>
</div>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>Fund</th>
			<th>Ticker</th>
			<th>Shares</th>
			<th>Total Value</th>

		</tr>
	</thead>
	<tbody>
	             	<c:forEach var="fund" items="${fundList}">
						 <tr>
                    		<td>${ fund.getFundName() }</td>
                    		<td> ${ fund.getTicker() } </td>
                    		<td> ${ fund.getShares() } </td>
                    		<td> ${ fund.getTotal() } </td>
                  			</tr>
					</c:forEach>
	</tbody>
</table>

<p>
	<br>
</p>

<jsp:include page="template-buttom.jsp" />