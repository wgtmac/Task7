<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />
                
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            NAME OF CUSTOMER
                        </h1>
                        <h2> Address
                        </h2>
                   </div>
                </div>
                <div class="form-group">
                                <label>Last Transaction Day</label>
                                <input class="form-control" disabled>
                                <p class="help-block">The above date is the date when you last performed any transaction</p>
                            </div>
                            <div class="form-group">
                                <label>Cash Balance</label>
                                <input class="form-control" disabled>
                                <p class="help-block">The total amount in your account</p>
                            </div>
                            <div class="form-group">
                                <label>Your Funds</label>
                                </div>
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Fund</th>
                                        <th>Number Of shares</th>
                                        <th>Total Value</th>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>GOOG</td>
                                        <td>126</td>
                                        <td>32333</td>
                                        
                                    </tr>
                                    <tr>
                                        <td>AAPL</td>
                                        <td>261</td>
                                        <td>338988</td>
                                        
                                    </tr>
                                    
                                    
                                </tbody>
                            </table>
                <p><br>
                </p>
                
<jsp:include page="template-buttom.jsp" />