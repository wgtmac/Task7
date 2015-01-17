<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />
                
				
                <div class="form-group">
                <h1> BROWSE THROUGH AVAILABLE FUNDS </h1>
                                <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Fund</th>
                                        <th>Ticker</th>
                                        <th>Current Value</th>
                                        <th>Previous CLose</th>
                                        <th>Net Assets</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="active">
                                        <td>GOOGLE</td>
                                        <td>GOO</td>
                                        <td>13.4</td>
                                        <td>12.33</td>
                                        <td>23b</td>
                                    </tr>
                                    <tr class="success">
                                        <td>APPLE</td>
                                        <td>AAPL</td>
                                        <td>23.3</td>
                                        <td>22.3</td>
                                        <td>54b</td>
                                    </tr>
                                    
                                </tbody>
                            </table>
                            </div>

                <br><br>
               
                
                
                <p><br>
                </p>
                
<jsp:include page="template-buttom.jsp" />