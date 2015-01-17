<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />
      
      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header"> Account Options </h2>
        </div>
      </div>
      <form action="customerPSW.do">
        <input type="submit" value="Change Password" class="btn btn-default">
      </form>
      <br>
      <form action="logout.do">
        <input type="submit" value="Log Out" class="btn btn-default">
      </form>
      
    <jsp:include page="template-buttom.jsp" />