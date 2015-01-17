<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />
      
      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header"> Change Password </h2>
        </div>
      </div>
      <div class="form-group">
        <label>New Password</label>
        <input class="form-control" type="password" placeholder="New Password">
      </div>
      <div class="form-group">
        <label>Confirm Password</label>
        <input class="form-control" type="password" placeholder="Confirm new Password">
      </div>
      <button type="submit" class="btn btn-default">Change Password</button>
      <p><br>
      </p>
      
 <jsp:include page="template-buttom.jsp" />