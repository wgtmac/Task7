<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />
      
      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header"> Transition Day </h2>
        </div>
      </div>
      
         <c:choose>
			<c:when test="${ (empty msg) }">
			</c:when>
			<c:otherwise>
				<h3 style="color: blue">${msg}</h3>
			</c:otherwise>
		</c:choose>
		
			<c:forEach var="error" items="${errors}">
				<h3 style="color: red">${error}</h3>
			</c:forEach>
      
      <form role="form" method="POST">
        <div class="row">
          <div class="col-lg-6">
            <h3>Funds to Update</h3>
            <div class="table-responsive">
              <table class="table table-bordered table-hover table-striped">
                <thead>
                  <tr>
                    <th>Name</th>
                    <th>Ticker</th>
                    <th>Price ($)</th>
                  </tr>
                </thead>
                <tbody>
                                
             		<c:forEach var="fund" items="${fundList}">
						 <tr>
                    		<td>${ fund.getName() }</td>
                    		<td> ${ fund.getSymbol() } </td>
                    		<td><input name="fund_${fund.getFundId()}" type="text" required class="form-control" value="${price_map.get(fund.getFundId())}"></td>
                  			</tr>
					</c:forEach>
               
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-3">
            <table class="table table-bordered table-hover table-striped">
              <thead>
                <tr class="success">
                  <th>Choose Trading Day: (yyyy-mm-dd)</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                
                  <td><input name="date" type="date" required class="form-control"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <br>
        <button type="submit" name="action" value="create" class="btn btn-primary">Submit Transition Day</button>
      </form>
      
<jsp:include page="template-buttom.jsp" />