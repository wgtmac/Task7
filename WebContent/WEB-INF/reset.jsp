<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />
      
      <div class="form-group">
        <h3>Reset password for: <font color="blue">Jeff Eppinger</font> </h3>
        <br>
        <label>New Password</label>
        <input class="form-control" type="password" placeholder="Case sensitive field">
        </div>
      <div class="form-group">
        <label>Confirm Password</label>
        <input class="form-control" type="password" placeholder="Case sensitive field">
      </div>
      <button type="submit" class="btn btn-default">Set New Password</button>
      <p><br>
      </p>
      
<jsp:include page="template-buttom.jsp" />