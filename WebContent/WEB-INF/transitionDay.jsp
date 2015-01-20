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
                    <th>Price</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Google Inc.</td>
                    <td> GOOG </td>
                    <td><input name="fundid1" type="text" required class="form-control" placeholder="Last Price $103.45"></td>
                  </tr>
                  <tr>
                    <td>Alibaba </td>
                    <td> ALBB </td>
                    <td><input name="fundid1" type="text" required class="form-control" placeholder="Last Price $89.45"></td>
                  </tr>
                  <tr>
                    <td>Amazon</td>
                    <td> AMZN </td>
                    <td><input name="fundid1" type="text" required class="form-control" placeholder="Last Price $33.45"></td>
                  </tr>
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
                  <th>Choose Trading Day: (mm/dd/yyyy)</th>
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