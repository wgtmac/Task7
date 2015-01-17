<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />
                
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="page-header">
                            Create Fund
                        </h2>
                   </div>
                </div>

                <form role="form">
                	<div class="form-group">
                    	<label>Fund Name:</label>
                        <input name="fundName" required class="form-control" placeholder="Max. 20 characters" maxlength="20">
                    </div>
                    
                    <div class="form-group">
                    	<label>Ticker:</label>
                        <input name="ticker" required class="form-control" placeholder="Capital letters ex. GOOG">
                    </div>
                    
               	  <div class="form-group">
                   	<label>Initial Price per Share:</label>
                      <input name="price" required class="form-control" placeholder="Only numbers in format 1000.00">
                    </div>
                     <button type="submit" name="create" class="btn btn-primary">Create Fund</button>
               </form>

               
<jsp:include page="template-buttom.jsp" />