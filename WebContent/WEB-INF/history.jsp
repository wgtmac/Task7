<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />
                
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="page-header">
                            Transaction History
                        </h2>
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
                                    <tr>
                                        <td><!-- Date -->01/12/2015</td>
                                        <td><!-- Boolean -->Pending</td>
                                        <td><!-- String -->Buy</td>
                                        <td><!-- String -->GOOG</td>
                                        <td align="right"><!-- Int --></td>
                                        <td align="right"><!-- float --></td>
                                        <td align="right"><!-- float -->$ 323.00</td>
                                    </tr>
                                    <tr>
                                        <td><!-- Date -->01/10/2015</td>
                                        <td><!-- Boolean -->Complete</td>
                                        <td><!-- String -->Deposit</td>
                                        <td><!-- String --></td>
                                        <td align="right"><!-- Int --></td>
                                        <td align="right"><!-- float --></td>
                                        <td align="right"><!-- float -->$ 1000.00</td>
                                    </tr>
                                </tbody>
                            </table>
                            <p align="center">*** End of Transactions ***</p>
                        </div>
                
<jsp:include page="template-buttom.jsp" />