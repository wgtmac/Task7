<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />
                
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="page-header">
                            Request Check
                        </h2>
                   </div>
                </div>
                 Please notice that your account balance as of today does not include pending transactions that will post at the end of the day. <br> You may only request a check for an amount equal or lesser than your "Current Available Balance".<br><br>
                <div class="col-lg-6">
               
						<form role="form">
                           <div class="form-group">
                                <label>Posted Account Balance:</label>
                                <p class="form-control-static">$ 1,500.00</p>
                                <label>Current Available Balance:</label>
                                <p class="form-control-static">$ 540.32</p>
								<br>
                                <label>Check Amount:</label>
                                <input type="text" class="form-control">
                                <p class="help-block">Enter only positive numbers with two decimals. Ex. 1530.00</p>
                        		 <label>Confirm Amount:</label>
                                <input type="text" class="form-control"><p>&nbsp;</p>
                            <button type="submit" class="btn btn-default">Request Check</button>
                          </form>
                        </div>
                
<jsp:include page="template-buttom.jsp" />