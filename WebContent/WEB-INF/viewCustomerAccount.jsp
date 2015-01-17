<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />

      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header"> Jeff Eppinger's Account </h2>
        </div>
 		<form action="reset.html">
   		&nbsp; <input type="submit" value="Reset Customer Password" name="username">
		</form>
      </div>
      <p>
      
      <div class="col-lg-6">
        <h2>Customer information:</h2>
        <div class="table-responsive">
          <table class="table table-hover table-striped">
            <thead>
              <tr>
                <td>Username:</td>
                <td>Jeff456</td>
              </tr>
                <tr>
                <td>Name:</td>
                <td>Jeff Eppinger</td>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Address:</td>
                <td>5000 Forbes Ave.</td>
              </tr>
                <tr>
                <td>City, State:</td>
                <td>Pittsburgh, PA.</td>
              </tr>
              <tr>
                <td>Last trading date:</td>
                <td>1/15/2015</td>
              </tr>
              <tr>
                <td>Accountable Balance as of Today:</td>
                <td align="right"><b>$ 90,010.00</b></td>
                </tr>
              <tr>
                <td>Available Cash:</td>
                <td align="right"><b>$ 14,000.00</b></td>
                </tr>
              </tbody>
          </table>
        </div>
        <h2> Funds owned:</h2>
        <div class="table-responsive">
          <table class="table table-hover table-striped">
            <thead>
              <tr>
                <th>Fund Name</th>
                <th>Shares</th>
                <th></th>
                <th>Value</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Google Inc</td>
                <td align="right">5000.522</td>
                <td align="right">$</td>
                <td align="right">80,000.00</td>
              </tr>
              <tr>
                <td>Alibaba Corp</td>
                <td align="right">46.830</td>
                <td align="right">$</td>
                <td align="right">8,000.00</td>
              </tr>
              <tr>
                <td>Apple Inc</td>
                <td align="right">0.500</td>
                <td align="right">$</td>
                <td align="right">10.00</td>
              </tr>
              <tr>
                <td>Exxon Mobile</td>
                <td align="right">130.043</td>
                <td align="right">$</td>
                <td align="right">1,990.10</td>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  
<jsp:include page="template-buttom.jsp" />