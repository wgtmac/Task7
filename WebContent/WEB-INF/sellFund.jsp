<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />

                <div class="row">
                  <div class="col-lg-12">
                <h2 class="page-header"> Sell Funds </h2>
                </div>
                </div>
                                <label>Select the fund you want to sell:</label>
                                <div class="form-group">
         <!-- Add the account stocks below -->                       
                                <select required class="form-control">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
          					</div>      
                            <div class="form-group">
                                <label>Number of shares you want to sell:</label>
                                <input type="number" class="form-control" id="inputSuccess"><br><br>                       
                				<button type="submit" class="btn btn-primary">Sell Funds</button>

								
<jsp:include page="template-buttom.jsp" />