<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />
                
                
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Change password
                        </h1>
                   </div>
                </div>
                <p> 
                
                
                
                <form role="form">
                	<div class="form-group">
                    	<label>Current password</label>
                        <input class="form-control" name="userName">
                    </div>
                    
                    <div class="form-group">
                    	<label>New password </label>
                        <input class="form-control" name="userName">
                    </div>
                    
                    <div class="form-group">
                    	<label>Confirm new password: </label>
                        <input class="form-control" name="userName">
                    </div>


                    <button type="submit" name="create" class="btn btn-default">Submit</button>
               

               </form>

                </p>
                <p><br>
          </p>
                
<jsp:include page="template-buttom.jsp" />